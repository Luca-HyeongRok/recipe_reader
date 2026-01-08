package com.example.myapplication.recipereader.feature.contacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.recipereader.core.ui.EmptyState
import com.example.myapplication.recipereader.core.ui.PermissionDenied

@Composable
fun ContactsScreen(
    onItemClick: (String) -> Unit,
    viewModel: ContactsViewModel = viewModel()
) {
    ContactsScreenContent(
        uiState = viewModel.uiState,
        onItemClick = onItemClick
    )
}

@Composable
fun ContactsScreenContent(
    uiState: ContactsUiState,
    onItemClick: (String) -> Unit
) {
    when {
        !uiState.hasPermission -> {
            PermissionDenied(
                title = "Contacts permission",
                message = "Enable contacts access to continue."
            )
        }
        uiState.items.isEmpty() -> {
            EmptyState(
                title = "No contacts",
                message = "No contacts to display."
            )
        }
        else -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.items) { item ->
                    ListItem(
                        headlineContent = { Text(item.name) },
                        modifier = Modifier.clickable { onItemClick(item.id) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactsScreenPreview() {
    ContactsScreenContent(
        uiState = ContactsUiState(
            hasPermission = true,
            items = listOf(ContactItem("1", "Preview Contact"))
        ),
        onItemClick = {}
    )
}
