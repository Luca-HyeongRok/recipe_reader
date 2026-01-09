package com.example.myapplication.recipereader.core.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.recipereader.presentation.contacts.ContactDetailScreen
import com.example.myapplication.recipereader.presentation.contacts.ContactsScreen
import com.example.myapplication.recipereader.presentation.gallery.GalleryScreen
import com.example.myapplication.recipereader.presentation.gallery.PhotoDetailScreen
import com.example.myapplication.recipereader.presentation.recipe.RecipeDetailScreen
import com.example.myapplication.recipereader.presentation.recipe.RecipeScreen

@Composable
fun RecipeReaderRouter(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Recipes.route
    ) {
        composable(Route.Recipes.route) {
            RecipeScreen(onRecipeClick = { })
        }
        composable(Route.Contacts.route) {
            ContactsScreen(
                onNavigateToDetail = { id ->
                    navController.navigate(Route.ContactDetail.createRoute(id))
                }
            )
        }
        composable(Route.Gallery.route) {
            GalleryScreen(
                onNavigateToDetail = { id ->
                    navController.navigate(Route.PhotoDetail.createRoute(id))
                }
            )
        }

        composable(
            route = Route.RecipeDetail.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            RecipeDetailScreen(id = backStackEntry.arguments?.getString("id").orEmpty())
        }
        composable(
            route = Route.ContactDetail.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            ContactDetailScreen(id = backStackEntry.arguments?.getString("id").orEmpty())
        }
        composable(
            route = Route.PhotoDetail.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            PhotoDetailScreen(id = backStackEntry.arguments?.getString("id").orEmpty())
        }
    }
}
