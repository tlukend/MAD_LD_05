package com.example.movieappmad23.repositories

import com.example.movieappmad23.data.MovieDao
import com.example.movieappmad23.models.Movie

class MovieRepository (private val movieDao: MovieDao) {

    suspend fun add(movie: Movie) = movieDao.add(movie)
    suspend fun delete(movie: Movie) = movieDao.delete(movie)
    suspend fun update(movie: Movie) = movieDao.update(movie)
    fun getAllMovies() = movieDao.getAllMovies()
    fun getAllFavoriteMovies() = movieDao.getAllFavoriteMovies()
    fun getMovieById(movieId : String) = movieDao.getMovieById(movieId)


    companion object{
        @Volatile
        private var instance: MovieRepository? = null
        fun getInstance(movieDao: MovieDao) =
            instance ?: synchronized(this){
                instance ?: MovieRepository(movieDao).also { instance = it }
            }
    }



}