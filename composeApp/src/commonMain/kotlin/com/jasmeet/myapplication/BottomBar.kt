package com.jasmeet.myapplication

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import myapplication.composeapp.generated.resources.Res
import myapplication.composeapp.generated.resources.compose_multiplatform
import myapplication.composeapp.generated.resources.ic_fav
import myapplication.composeapp.generated.resources.ic_home
import myapplication.composeapp.generated.resources.ic_profile
import myapplication.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val tabs = listOf(
        BottomNavItem(HomeTab, "Home", Res.drawable.ic_home),
        BottomNavItem(SearchTab, "Search", Res.drawable.ic_search),
        BottomNavItem(FavoritesTab, "Favorites", Res.drawable.ic_fav),
        BottomNavItem(ProfileTab, "Profile", Res.drawable.ic_profile)
    )

    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry.value?.destination?.route

    NavigationBar {
        tabs.forEach { tab ->
            val isSelected = currentDestination?.contains(tab.route::class.simpleName ?: "") == true
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(tab.icon),
                        contentDescription = tab.label,
                        tint = if(isSelected) Color.Blue else Color.Gray,
                        modifier = Modifier.size(34.dp)
                    )
                },
                label = {
                    Text(tab.label)
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(tab.route) {
                        // Pop up to the start destination of the bottom nav graph
                        popUpTo(HomeTab) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(
    val route: Any,
    val label: String,
    val icon: DrawableResource
)