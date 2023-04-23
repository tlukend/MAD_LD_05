package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository): ViewModel() {
    private val _movies = MutableStateFlow(listOf<Movie>())

    init {
        viewModelScope.launch {
            repository.getAllMovies().collect{movieList ->
                if (!movieList.isNullOrEmpty()) {
                    _movies.value = movieList
                }
            }
        }
    }

    suspend fun getAllMovies(){
        repository.getAllMovies()
    }

    suspend fun toggleIsFavorite(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }


}