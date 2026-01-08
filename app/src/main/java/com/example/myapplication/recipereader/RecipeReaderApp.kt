package com.example.myapplication.recipereader

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.recipereader.core.navigation.RecipeReaderNavGraph
import com.example.myapplication.recipereader.core.ui.RecipeReaderBottomBar

@Composable
fun RecipeReaderApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { RecipeReaderBottomBar(navController) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            RecipeReaderNavGraph(navController)
        }
    }
}
