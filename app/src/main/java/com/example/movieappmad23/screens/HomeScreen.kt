package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad23.utils.InjectorUtils
import com.example.movieappmad23.viewmodels.HomeViewModel
import com.example.movieappmad23.widgets.HomeTopAppBar
import com.example.movieappmad23.widgets.MovieRow
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController = rememberNavController()){
    //val coroutineScope = rememberCoroutineScope()
    val viewModel : HomeViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(
        LocalContext.current))
    //val movieListState by viewModel.movies.collectAsState()

    //for more complicated code, read on bottom

    Scaffold(topBar = {
        HomeTopAppBar(
            title = "Home",
            menuContent = {
                DropdownMenuItem(onClick = { navController.navigate(Screen.AddMovieScreen.route) }) {
                    Row {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Add Movie", modifier = Modifier.padding(4.dp))
                        Text(text = "Add Movie", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                    }
                }
                DropdownMenuItem(onClick = { navController.navigate(Screen.FavoriteScreen.route) }) {
                    Row {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites", modifier = Modifier.padding(4.dp))
                        Text(text = "Favorites", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                    }
                }
            }
        )
    }) { padding ->
        MainContent(
            modifier = Modifier.padding(padding),
            navController = navController,
            viewModel = viewModel
            //coroutineScope = coroutineScope
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: HomeViewModel
    //coroutineScope: CoroutineScope
) {
    MovieList(
        modifier = modifier,
        navController = navController,
        viewModel = viewModel,
        //coroutineScope = coroutineScope
    )
}

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel,
    //coroutineScope: CoroutineScope
) {
    val coroutineScope = rememberCoroutineScope()
    val movieListState by viewModel.movies.collectAsState()

    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {


        items(items = movieListState) { movieItem ->
            MovieRow(
                movie = movieItem,
                onMovieRowClick = { movieId ->
                    navController.navigate(Screen.DetailScreen.withId(movieId))
                },
                onFavClick  = {movie ->
                    coroutineScope.launch {
                        viewModel.toggleIsFavorite(movieItem)
                    }
                }
            )
        }
    }
}

//thanks to Injectorutils, no need to write all those lines - simplified above
/*
val db = MovieDatabase.getDatabase(LocalContext.current) //context in which composable exists right now
val repository = MovieRepository(movieDao = db.movieDao()) //instance of repo, dao from db, we defined it there
val factory = MovieViewModelFactory(repository) // as we defined in VMFactory
val viewModel: MoviesViewModel = viewModel(factory = factory) //viewmodel can use this factory now

val movieListState = viewModel.movieListState.collectAsState()
val coroutineScope = rememberCoroutineScope()
*/
/* val coroutineScope = rememberCoroutineScope()
 val homeViewModel: HomeViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(LocalContext.current))
 val movieList by homeViewModel.movie.collectAsState()
*/
