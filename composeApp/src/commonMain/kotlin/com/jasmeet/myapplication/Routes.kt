package com.jasmeet.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
object Screen1

@Serializable
object BottomNavGraph

@Serializable
object HomeTab

@Serializable
object SearchTab

@Serializable
object FavoritesTab

@Serializable
object ProfileTab

@Serializable
data class Screen3(val fromTab: String)


data class TabDestination(
    val route: Any,
    val name: String,
    val routeString: String
)



fun NavGraphBuilder.addMainScreens(navController: NavHostController) {
    composable<Screen1> {
        Screen1Content(navController = navController)
    }

    composable<Screen3> { backStackEntry ->
        val screen3 = backStackEntry.toRoute<Screen3>()
        Screen3Content(
            navController = navController,
            fromTab = screen3.fromTab
        )
    }
}

fun NavGraphBuilder.addBottomNavScreens(navController: NavHostController) {
    navigation<BottomNavGraph>(startDestination = HomeTab) {
        composable<HomeTab> {
            TabContent(navController, "Home", "home")
        }

        composable<SearchTab> {
            TabContent(navController, "Search", "search")
        }

        composable<FavoritesTab> {
            TabContent(navController, "Favorites", "favorites")
        }

        composable<ProfileTab> {
            TabContent(navController, "Profile", "profile")
        }
    }
}