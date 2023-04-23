package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel (private val repository: MovieRepository): ViewModel() {
    private val _favMovies = MutableStateFlow(listOf<Movie>())

    init {
        viewModelScope.launch {
            repository.getAllFavoriteMovies().collect{movieList ->
                if (!movieList.isNullOrEmpty()) {
                    _favMovies.value = movieList
                }
            }
        }
    }

    suspend fun getAllFavMovies(){
        repository.getAllFavoriteMovies()
    }

    suspend fun addFavMovie(movie: Movie){
        repository.add(movie)
    }

    suspend fun deleteFavMovie(movie: Movie){
        repository.delete(movie)
    }
    //TODO: use this instead of MoviesViewModelupdateFav
    fun updateFavoriteMovies(movie: Movie){
        movie.isFavorite = !movie.isFavorite
    }
}