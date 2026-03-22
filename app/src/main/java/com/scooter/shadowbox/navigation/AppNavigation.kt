package com.scooter.shadowbox.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.scooter.shadowbox.UI.editor.EditorScreen
import com.scooter.shadowbox.UI.home.HomeScreen


sealed class Screen(val route: String) {
        object Home : Screen("home")
        object Editor : Screen("editor?connectionId={connectionId}") {
            fun passID(id: String? = null): String {
                return if (id != null) "editor?connectionId=$id" else "editor"
            }
        }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onAddConnection = { navController.navigate(Screen.Editor.passID()) },
                onEditConnection = { id -> navController.navigate(Screen.Editor.passID())},
                onLogout = {},
                onSettings = {}
            )
        }
        composable(
            route = Screen.Editor.route,
            arguments = listOf(
                navArgument("connectionId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val connectionId = backStackEntry.arguments?.getString("connectionId")
            EditorScreen(
                connectionId = connectionId,
                onNavigateBack = { navController.popBackStack() })
        }
    }
}