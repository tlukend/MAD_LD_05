package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MovieRepository, private val movieId : String): ViewModel() {

    // no other initialization needed, since it only shows one movie
    var movieState = MutableStateFlow(Movie())

    init{
        viewModelScope.launch{
            repository.getMovieById(movieId)    //get the movie you need for the screen
        }
    }

    //needs to get the movie from DB by ID
    suspend fun getMovieById(movieId: String){
        repository.getMovieById(movieId)
    }
    //still must be able to change to favorite or opposite
    suspend fun toggleIsFavorite(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }

}