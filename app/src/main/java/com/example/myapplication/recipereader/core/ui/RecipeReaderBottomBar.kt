package com.example.myapplication.recipereader.core.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.recipereader.core.navigation.Destinations

private data class BottomNavItem(
    val destination: Destinations,
    val label: String,
    val icon: ImageVector
)

@Composable
fun RecipeReaderBottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(Destinations.Recipes, "Recipes", Icons.Outlined.Restaurant),
        BottomNavItem(Destinations.Contacts, "Contacts", Icons.Outlined.Contacts),
        BottomNavItem(Destinations.Gallery, "Gallery", Icons.Outlined.Photo)
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route.orEmpty()

    NavigationBar {
        items.forEach { item ->
            val selected = currentRoute.startsWith(item.destination.route)
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.destination.route) {
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
