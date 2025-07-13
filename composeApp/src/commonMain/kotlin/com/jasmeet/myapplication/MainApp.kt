package com.jasmeet.myapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview



@Composable
internal fun MainApp(navController: NavHostController = rememberNavController()) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState()

    // Check if we're in the bottom navigation graph
    val showBottomBar = currentBackStackEntry.value?.destination?.parent?.route?.contains("BottomNavGraph") == true

    // Set up deep link handling following official pattern
    DisposableEffect(Unit) {
        println("🔗 MainApp: Setting up deep link listener")

        // Cleaner version using helper functions:

        ExternalUriHandler.listener = { uri ->
            try {
                println("🔗 MainApp: Attempting to navigate to: $uri")

                when {
                    uri.contains("/user/") -> {
                        val userId = extractPathParameter(uri, "user")
                        val section = parseQueryParameter(uri, "section", "profile")

                        println("🔗 Parsed UserDetail: userId='$userId', section='$section'")
                        navController.navigate(UserDetail(userId = userId, section = section))
                    }

                    uri.contains("/plant/") -> {
                        val plantId = extractPathParameter(uri, "plant")
                        val category = parseQueryParameter(uri, "category", "flower")

                        println("🔗 Parsed PlantDetail: plantId='$plantId', category='$category'")
                        navController.navigate(PlantDetail(plantId = plantId, category = category))
                    }

                    uri.contains("/product/") -> {
                        val productId = extractPathParameter(uri, "product")
                        val name = parseQueryParameter(uri, "name", "")
                        // For now, handle tags as empty list - can be enhanced later
                        val tags = emptyList<String>()

                        println("🔗 Parsed ProductDetail: productId='$productId', name='$name'")
                        navController.navigate(ProductDetail(productId = productId, name = name, tags = tags))
                    }

                    else -> {
                        println("🔗 Unknown deep link pattern: $uri")
                        navController.navigate(HomeScreen)
                    }
                }

                println("🔗 MainApp: Navigation successful to: $uri")
            } catch (e: Exception) {
                println("🔗 MainApp: Failed to navigate to deep link: $uri, error: ${e.message}")
                e.printStackTrace()

                // Fallback to home
                try {
                    navController.navigate(HomeScreen)
                } catch (fallbackError: Exception) {
                    println("🔗 Even fallback failed: ${fallbackError.message}")
                }
            }
        }

        // Removes the listener when the composable is no longer active
        onDispose {
            println("🔗 MainApp: Removing deep link listener")
            ExternalUriHandler.listener = null
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeScreen,
            modifier = Modifier.padding(innerPadding)
        ) {
            addMainScreens(navController)
            addBottomNavScreens(navController)
        }
    }
}

/**
 * Helper function to parse query parameters from a URL
 */
fun parseQueryParameter(url: String, paramName: String, defaultValue: String = ""): String {
    val queryString = url.substringAfter("?", "")
    return if (queryString.contains("$paramName=")) {
        queryString.substringAfter("$paramName=").substringBefore("&")
    } else {
        defaultValue
    }
}

/**
 * Helper function to extract path parameter after a specific segment
 */
fun extractPathParameter(url: String, afterSegment: String): String {
    return url.substringAfter("/$afterSegment/").substringBefore("?").substringBefore("&")
}