package com.example.myapplication.recipereader

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.recipereader.core.routing.RecipeReaderRouter
import com.example.myapplication.recipereader.core.routing.Route

@Composable
fun RecipeReaderApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { RecipeReaderBottomBar(navController) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            RecipeReaderRouter(navController)
        }
    }
}

private data class BottomItem(
    val route: Route,
    val label: String,
    val icon: ImageVector
)

@Composable
private fun RecipeReaderBottomBar(navController: NavController) {
    val items = listOf(
        BottomItem(Route.Recipes, "Recipes", Icons.Outlined.Restaurant),
        BottomItem(Route.Contacts, "Contacts", Icons.Outlined.Contacts),
        BottomItem(Route.Gallery, "Gallery", Icons.Outlined.Photo)
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route.orEmpty()

    NavigationBar {
        items.forEach { item ->
            val selected = currentRoute.startsWith(item.route.route)
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
