package com.example.myapplication.recipereader.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.recipereader.feature.contacts.ContactDetailScreen
import com.example.myapplication.recipereader.feature.contacts.ContactsScreen
import com.example.myapplication.recipereader.feature.gallery.GalleryScreen
import com.example.myapplication.recipereader.feature.gallery.PhotoDetailScreen
import com.example.myapplication.recipereader.feature.recipes.RecipeDetailScreen
import com.example.myapplication.recipereader.feature.recipes.RecipesScreen

@Composable
fun RecipeReaderNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Recipes.route
    ) {
        composable(Destinations.Recipes.route) {
            RecipesScreen(
                onItemClick = { id ->
                    navController.navigate(Destinations.RecipeDetail.createRoute(id))
                }
            )
        }
        composable(Destinations.Contacts.route) {
            ContactsScreen(
                onItemClick = { id ->
                    navController.navigate(Destinations.ContactDetail.createRoute(id))
                }
            )
        }
        composable(Destinations.Gallery.route) {
            GalleryScreen(
                onItemClick = { id ->
                    navController.navigate(Destinations.PhotoDetail.createRoute(id))
                }
            )
        }

        composable(Destinations.RecipeDetail.route) { backStackEntry ->
            RecipeDetailScreen(
                id = backStackEntry.arguments?.getString("id").orEmpty()
            )
        }
        composable(Destinations.ContactDetail.route) { backStackEntry ->
            ContactDetailScreen(
                id = backStackEntry.arguments?.getString("id").orEmpty()
            )
        }
        composable(Destinations.PhotoDetail.route) { backStackEntry ->
            PhotoDetailScreen(
                id = backStackEntry.arguments?.getString("id").orEmpty()
            )
        }
    }
}
