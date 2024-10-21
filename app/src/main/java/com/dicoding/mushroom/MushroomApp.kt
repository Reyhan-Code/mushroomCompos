package com.dicoding.mushroom



import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mushroom.ui.theme.MushroomTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.mushroom.Navigation.Screen
import com.dicoding.mushroom.component.BottomBar
import com.dicoding.mushroom.ui.view.detail.DetailView
import com.dicoding.mushroom.ui.view.home.HomeView
import com.dicoding.mushroom.ui.view.profile.ProfileView


@Composable
fun MushroomApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    ) {

    Scaffold(
        bottomBar = {
            BottomBar(
                navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp)
                    .size(65.dp)
            )
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route){
                HomeView(navController)
            }
            composable(Screen.Profile.route){
                ProfileView()
            }
            composable("detail/{mushromId}") { backStackEntry ->
                DetailView(navController, backStackEntry.arguments?.getString("mushromId") ?: "")
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview() {
    MushroomTheme {
        MushroomApp()
    }
}