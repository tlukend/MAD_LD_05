package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappmad23.utils.InjectorUtils
import com.example.movieappmad23.viewmodels.FavoriteViewModel
import com.example.movieappmad23.widgets.MovieRow
import com.example.movieappmad23.widgets.SimpleTopAppBar
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController){
    val viewModel : FavoriteViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(
        LocalContext.current)) //initialize a VM using logic from FavVM
    val favListState by viewModel.favMovies.collectAsState()
    val coroutineScope = rememberCoroutineScope()



    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "My Favorite Movies")
        }
    }){ padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(favListState){ movie ->
                    MovieRow(
                        movie = movie,
                        onMovieRowClick = { movieId ->
                            navController.navigate(route = Screen.DetailScreen.withId(movieId))
                        },
                        onFavClick = {
                            coroutineScope.launch{
                                viewModel.toggleIsFavorite(movie)
                            }
                        }
                    )
                }
            }
        }
    }
}