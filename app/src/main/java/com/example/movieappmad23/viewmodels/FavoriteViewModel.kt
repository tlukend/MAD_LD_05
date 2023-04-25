package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel (private val repository: MovieRepository): ViewModel() {
    private val _favMovies = MutableStateFlow(listOf<Movie>()) //is there to update UI
    val favMovies: StateFlow<List<Movie>> = _favMovies.asStateFlow() //for updating the flow
    //initialize with list of all favorite movies
    init {
        viewModelScope.launch {
            repository.getAllFavoriteMovies()
                .collect{movieList ->
                //originally we would check with if(isEmpty) for NullPointerException, but no need anymore, list cant be null
                _favMovies.value = movieList

            }
        }
    }
    suspend fun toggleIsFavorite(movie: Movie){
        movie.isFavorite =  !movie.isFavorite
        repository.update(movie)
    }
}