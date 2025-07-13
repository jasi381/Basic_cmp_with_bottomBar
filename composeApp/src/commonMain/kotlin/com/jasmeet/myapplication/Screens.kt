package com.jasmeet.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreenContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate(BottomNavGraph)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Bottom Navigation")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Test Deep Link Navigation:",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Test deep link navigation buttons
        Button(
            onClick = {
                navController.navigate(PlantDetail(plantId = "rose-001", category = "flower"))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Plant Detail (Rose)")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate(UserDetail(userId = "user-123", section = "settings"))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("User Detail (Settings)")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate(
                    ProductDetail(
                        productId = "prod-456",
                        name = "Awesome Product",
                        tags = listOf("electronics", "gadget", "new")
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Product Detail (Complex)")
        }
    }
}

@Composable
fun PlantDetailContent(
    navController: NavHostController,
    plantId: String,
    category: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "🌱 Plant Details",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Plant ID: $plantId",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Category: $category",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "This plant was opened via deep link!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Back")
            }

            Button(
                onClick = {
                    navController.navigate(HomeScreen) {
                        popUpTo(HomeScreen) { inclusive = true }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Home")
            }
        }
    }
}

@Composable
fun UserDetailContent(
    navController: NavHostController,
    userId: String,
    section: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "👤 User Profile",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "User ID: $userId",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Section: $section",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Opened via deep link navigation",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Back")
            }

            Button(
                onClick = {
                    navController.navigate(BottomNavGraph)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Dashboard")
            }
        }
    }
}

@Composable
fun ProductDetailContent(
    navController: NavHostController,
    productId: String,
    name: String,
    tags: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "🛍️ Product Details",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Product ID: $productId",
                    style = MaterialTheme.typography.bodyLarge
                )

                if (name.isNotEmpty()) {
                    Text(
                        text = "Name: $name",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                if (tags.isNotEmpty()) {
                    Text(
                        text = "Tags:",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    LazyColumn {
                        items(tags) { tag ->
                            Text(
                                text = "• $tag",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Complex parameters passed via deep link",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Back")
            }

            Button(
                onClick = {
                    navController.navigate(HomeScreen) {
                        popUpTo(HomeScreen) { inclusive = true }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Home")
            }
        }
    }
}

@Composable
fun TabContent(navController: NavHostController, tabName: String, tabRoute: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tab: $tabName",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate(DetailScreen(fromTab = tabRoute))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Detail from $tabName")
        }

        // Add deep link navigation from specific tabs
        if (tabName == "Search") {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate(PlantDetail(plantId = "search-result-001", category = "search"))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search Result Plant")
            }
        }

        if (tabName == "Profile") {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate(UserDetail(userId = "current-user", section = "profile"))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("My Profile Details")
            }
        }
    }
}

@Composable
fun DetailScreenContent(navController: NavHostController, fromTab: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Detail Screen",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Came from: ${fromTab.replaceFirstChar { it.uppercase() }} tab",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Back")
            }

            Button(
                onClick = {
                    navController.navigate(HomeScreen) {
                        popUpTo(HomeScreen) { inclusive = true }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Home")
            }
        }
    }
}