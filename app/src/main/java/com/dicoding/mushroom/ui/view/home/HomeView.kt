package com.dicoding.mushroom.ui.view.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dicoding.mushroom.Navigation.Screen
import com.dicoding.mushroom.component.MushroomItem
import com.dicoding.mushroom.data.model.Mushroom
import com.dicoding.mushroom.helper.di.Injection
import com.dicoding.mushroom.helper.state.UiState
import com.dicoding.mushroom.ui.view.ViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mushrooms") }
            )

        },
        content = { paddingValues ->
            viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        viewModel.getAllMoviesAnime()
                    }
                    is UiState.Success -> {
                        Home(
                            listMushrom = uiState.data,
                            navController = navController,
                            modifier = modifier.padding(paddingValues)
                        )
                    }
                    is UiState.Error -> {}
                }
            }
        }
    )

}


@Composable
fun Home(
    listMushrom: List<Mushroom>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn {
            items(listMushrom) {
                MushroomItem(
                    name = it.name,
                    image = it.image,
                    edible = it.edible,
                    description = it.description,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Detail.createRoute(it.id))
                    }
                )
            }
        }
    }
}