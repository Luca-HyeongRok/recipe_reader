package com.example.myapplication.recipereader.presentation.contacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.recipereader.domain.model.Contact
import com.example.myapplication.recipereader.ui.EmptyState
import com.example.myapplication.recipereader.ui.PermissionDenied

@Composable
fun ContactsScreen(
    onNavigateToDetail: (String) -> Unit,
    viewModel: ContactsViewModel = viewModel(
        factory = ContactsViewModelFactory(
            context = LocalContext.current,
            contentResolver = LocalContext.current.contentResolver
        )
    )
) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(ContactsUiEvent.LoadContacts)
        viewModel.effect.collect { effect ->
            when (effect) {
                is ContactsUiEffect.NavigateToDetail -> onNavigateToDetail(effect.id)
            }
        }
    }

    ContactsScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ContactsScreenContent(
    uiState: ContactsUiState,
    onEvent: (ContactsUiEvent) -> Unit
) {
    when {
        !uiState.hasPermission && uiState.items.isEmpty() -> {
            PermissionDenied(
                title = "Contacts permission",
                message = "Enable contacts access to continue."
            )
        }
        else -> {
            Column(modifier = Modifier.fillMaxSize()) {
                ContactsSearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = { onEvent(ContactsUiEvent.OnSearchQueryChange(it)) }
                )
                if (!uiState.hasPermission) {
                    PermissionHint()
                } else if (uiState.isUsingMock) {
                    MockHint()
                }
                if (uiState.items.isEmpty()) {
                    EmptyState(
                        title = "No contacts",
                        message = "No contacts found for your search.",
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(uiState.items) { item ->
                            ContactRow(
                                contact = item,
                                onClick = { onEvent(ContactsUiEvent.OnContactClick(item.id)) },
                                onToggleFavorite = { isFavorite ->
                                    onEvent(
                                        ContactsUiEvent.OnToggleFavorite(
                                            id = item.id,
                                            isFavorite = isFavorite
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactsSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text("초성 검색") },
        placeholder = { Text("예: ㄱ, ㅂ, ㅇ") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
private fun PermissionHint() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Text(text = "Contacts permission not granted.")
        Text(text = "Showing sample contacts instead.")
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun MockHint() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Text(text = "No device contacts found.")
        Text(text = "Showing sample contacts.")
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun ContactRow(
    contact: Contact,
    onClick: () -> Unit,
    onToggleFavorite: (Boolean) -> Unit
) {
    ListItem(
        headlineContent = { Text(contact.name) },
        supportingContent = { Text(contact.phoneNumber) },
        trailingContent = {
            IconButton(onClick = { onToggleFavorite(!contact.isFavorite) }) {
                if (contact.isFavorite) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "Unfavorite"
                    )
                } else {
                    Icon(
                        imageVector = Icons.Rounded.StarBorder,
                        contentDescription = "Favorite"
                    )
                }
            }
        },
        modifier = Modifier.clickable { onClick() }
    )
}

@Preview(showBackground = true)
@Composable
private fun ContactsScreenPreview() {
    ContactsScreenContent(
        uiState = ContactsUiState(
            hasPermission = true,
            items = listOf(
                Contact(
                    id = "1",
                    name = "김민지",
                    phoneNumber = "010-1234-5678",
                    isFavorite = true
                )
            )
        ),
        onEvent = {}
    )
}
