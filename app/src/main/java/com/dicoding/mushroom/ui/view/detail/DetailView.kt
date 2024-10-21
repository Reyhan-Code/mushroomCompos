package com.dicoding.mushroom.ui.view.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.mushroom.R
import com.dicoding.mushroom.data.model.Mushroom
import com.dicoding.mushroom.helper.di.Injection
import com.dicoding.mushroom.helper.state.UiState
import com.dicoding.mushroom.ui.view.ViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView (
    navController: NavHostController,
    mushromId: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Mushrooms") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.back_button),
                            contentDescription = "Back Button"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        viewModel.getMushroomById(mushromId.toLong())
                    }
                    is UiState.Success -> {
                        Detail(
                            mushrooms = uiState.data,
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
fun Detail(
    mushrooms: Mushroom,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Text(
                text = mushrooms.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        item {
            Image(
                painter = painterResource(mushrooms.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }

        item {
            Text(
                text = "Edible?: ${mushrooms.edible}",
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        item {
            Text(
                text = mushrooms.description,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

    }
}


@Preview
@Composable
fun DetailScreenPreview() {
    val navController = rememberNavController()
    DetailView(navController = navController, mushromId = "1")
}