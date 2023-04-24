package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AddMovieViewModel(private val repository: MovieRepository): ViewModel() {

    suspend fun addMovie(movie: Movie){
        repository.add(movie)
    }

    //TODO: probably not gonna need this
    suspend fun deleteMovie(movie: Movie){
        repository.delete(movie)
    }

    suspend fun validateUserInput(input: String): Boolean{
        return input.isEmpty() //isEmpty returns true or false
    }


}