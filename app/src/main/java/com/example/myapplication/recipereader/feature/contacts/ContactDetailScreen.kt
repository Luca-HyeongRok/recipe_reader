package com.example.myapplication.recipereader.feature.contacts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ContactDetailScreen(id: String) {
    // TODO: Load detail from ContactsProvider.
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Contact Detail", style = MaterialTheme.typography.titleLarge)
        Text(text = "id = $id", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactDetailScreenPreview() {
    ContactDetailScreen(id = "1")
}
