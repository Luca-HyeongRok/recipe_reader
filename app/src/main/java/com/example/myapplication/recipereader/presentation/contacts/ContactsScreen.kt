package com.example.myapplication.recipereader.presentation.contacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.recipereader.ui.EmptyState
import com.example.myapplication.recipereader.ui.PermissionDenied

@Composable
fun ContactsScreen(
    onNavigateToDetail: (String) -> Unit,
    viewModel: ContactsViewModel = viewModel()
) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
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
        !uiState.hasPermission -> {
            PermissionDenied(
                title = "Contacts permission",
                message = "Enable contacts access to continue."
            )
        }
        uiState.items.isEmpty() -> {
            Column(modifier = Modifier.fillMaxSize()) {
                ContactsSearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = { onEvent(ContactsUiEvent.OnSearchQueryChange(it)) }
                )
                EmptyState(
                    title = "No contacts",
                    message = "ContactsProvider integration coming later.",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        else -> {
            Column(modifier = Modifier.fillMaxSize()) {
                ContactsSearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = { onEvent(ContactsUiEvent.OnSearchQueryChange(it)) }
                )
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.items) { item ->
                        ListItem(
                            headlineContent = { Text(item.name) },
                            modifier = Modifier.clickable {
                                onEvent(ContactsUiEvent.OnContactClick(item.id))
                            }
                        )
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

@Preview(showBackground = true)
@Composable
private fun ContactsScreenPreview() {
    ContactsScreenContent(
        uiState = ContactsUiState(
            hasPermission = true,
            items = listOf(ContactItem("1", "Preview Contact"))
        ),
        onEvent = {}
    )
}
