package com.jasmeet.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import androidx.navigation.navDeepLink


// Main navigation routes
@Serializable
object HomeScreen

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
data class DetailScreen(val fromTab: String)

// Deep linkable routes following official pattern
@Serializable
data class PlantDetail(
    val plantId: String,
    val category: String = "flower"
)

@Serializable
data class UserDetail(
    val userId: String,
    val section: String = "profile"
)

@Serializable
data class ProductDetail(
    val productId: String,
    val name: String = "",
    val tags: List<String> = emptyList()
)

// Navigation graph builders
fun NavGraphBuilder.addMainScreens(navController: NavHostController) {
    // Home screen
    composable<HomeScreen> {
        HomeScreenContent(navController = navController)
    }

    // Detail screen from tabs
    composable<DetailScreen> { backStackEntry ->
        val detail = backStackEntry.toRoute<DetailScreen>()
        DetailScreenContent(
            navController = navController,
            fromTab = detail.fromTab
        )
    }

    // Deep linkable screens following official pattern

    // Plant detail with multiple deep link patterns
    composable<PlantDetail>(
        deepLinks = listOf(
            // Using generated URI pattern (recommended approach)
            navDeepLink<PlantDetail>(basePath = "demo://example.com/plant"),
            // Using manual URI pattern (alternative approach)
            navDeepLink {
                uriPattern = "demo://example.com/plants/{plantId}?category={category}"
            },
            // HTTPS deep links
            navDeepLink<PlantDetail>(basePath = "https://myapp.example.com/plant")
        )
    ) { backStackEntry ->
        val plantDetail = backStackEntry.toRoute<PlantDetail>()
        PlantDetailContent(
            navController = navController,
            plantId = plantDetail.plantId,
            category = plantDetail.category
        )
    }

    // User detail with generated pattern
    composable<UserDetail>(
        deepLinks = listOf(
            navDeepLink<UserDetail>(basePath = "demo://example.com/user"),
            navDeepLink<UserDetail>(basePath = "https://myapp.example.com/user")
        )
    ) { backStackEntry ->
        val userDetail = backStackEntry.toRoute<UserDetail>()
        UserDetailContent(
            navController = navController,
            userId = userDetail.userId,
            section = userDetail.section
        )
    }

    // Product detail with complex parameters
    composable<ProductDetail>(
        deepLinks = listOf(
            navDeepLink<ProductDetail>(basePath = "demo://example.com/product"),
            navDeepLink<ProductDetail>(basePath = "https://myapp.example.com/product")
        )
    ) { backStackEntry ->
        val productDetail = backStackEntry.toRoute<ProductDetail>()
        ProductDetailContent(
            navController = navController,
            productId = productDetail.productId,
            name = productDetail.name,
            tags = productDetail.tags
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