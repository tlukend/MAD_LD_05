package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.utils.InjectorUtils
import com.example.movieappmad23.viewmodels.DetailViewModel
import com.example.movieappmad23.widgets.HorizontalScrollableImageView
import com.example.movieappmad23.widgets.MovieRow
import com.example.movieappmad23.widgets.SimpleTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    navController: NavController,
    movieId:String){

    //TODO: why can i not add my provideDetailFactory??
    val viewModel : DetailViewModel = viewModel(factory = InjectorUtils.pro)
    val coroutineScope = rememberCoroutineScope()

    val movie = viewModel.movieState.collectAsState()

    movieId?.let {
        //val movie = viewModel.movieListState.value.filter { it.id == movieId  }[0]
        val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`

        Scaffold(scaffoldState = scaffoldState, // attaching `scaffoldState` to the `Scaffold`
            topBar = {
                SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                    Text(text = movie.value.title) //movie.title changed to movie.value.title (because from abstract)
                }
            },
        ) { padding ->
            MainContent(
                Modifier.padding(padding), movie.value, viewModel, coroutineScope) //movie = movie.value
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    movie: Movie,
    viewModel: DetailViewModel,
    coroutineScope: CoroutineScope
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            MovieRow(
                movie = movie,
                onFavClick = { movie ->
                    coroutineScope.launch {
                        viewModel.toggleIsFavorite(movie)
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Divider()

            Text(text = "Movie Images", style = MaterialTheme.typography.h5)

            HorizontalScrollableImageView(movie = movie)
        }
    }
}