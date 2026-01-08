package com.example.myapplication.recipereader.presentation.gallery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PhotoDetailScreen(id: String) {
    // TODO: Load photo detail from MediaStore.
    // TODO: Display EXIF info in detail screen.
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Photo Detail", style = MaterialTheme.typography.titleLarge)
        Text(text = "id = $id", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotoDetailScreenPreview() {
    PhotoDetailScreen(id = "1")
}
