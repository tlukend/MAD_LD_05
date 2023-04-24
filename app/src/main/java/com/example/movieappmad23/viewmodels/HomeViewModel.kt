package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository): ViewModel() {
    private val _movies = MutableStateFlow(listOf<Movie>())
    //stateflow receives all updates from downstream flow & stores latest value
    val movie: StateFlow<List<Movie>> = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllMovies().collect{movieList ->
                if (!movieList.isNullOrEmpty()) {
                    _movies.value = movieList
                }
            }
        }
    }
    //Homescreen shows all movies
    suspend fun getAllMovies(){
        repository.getAllMovies()
    }
    //homescreen must be able to change to (!)favorite
    suspend fun toggleIsFavorite(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }


}