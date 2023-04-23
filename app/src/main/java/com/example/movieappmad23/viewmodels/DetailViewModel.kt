package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MovieRepository): ViewModel() {

    //TODO: this might be wrong

    private val _movie : Movie? = null

    init {
        viewModelScope.launch {
            if (_movie != null) {
                repository.getMovieById(_movie)
            }
        }
    }


    suspend fun getMovieById(movie: Movie){
        repository.getMovieById(movie)
    }
}