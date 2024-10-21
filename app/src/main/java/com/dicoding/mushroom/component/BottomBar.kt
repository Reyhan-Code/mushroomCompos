package com.dicoding.mushroom.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dicoding.mushroom.Navigation.BottomBarItem
import com.dicoding.mushroom.Navigation.Screen
import com.dicoding.mushroom.R
import com.dicoding.mushroom.ui.theme.CoffeeBrown

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
           BottomBarItem(
                icon = R.drawable.homeclick,
                selectedIcon = R.drawable.home,
                screen = Screen.Home
            ),
            BottomBarItem(
                icon = R.drawable.profileclick,
                selectedIcon = R.drawable.profile,
                screen = Screen.Profile,
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (currentRoute == item.screen.route)
                                item.selectedIcon else item.icon
                        ),
                        contentDescription = "Item",
                        modifier = Modifier.size(27.dp),
                        tint = CoffeeBrown
                    )
                },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}