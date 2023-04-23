package com.example.movieappmad23.data

import androidx.room.*
import com.example.movieappmad23.models.Movie
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {
    @Insert
    suspend fun add (movie: Movie)

    @Update
    suspend fun update (movie: Movie)

    @Delete
    suspend fun delete (movie: Movie)

    //gettAllMovies
    @Query("SELECT * from movie")
    fun getAllMovies(): Flow<List<Movie>>

    //getAllFavs
    @Query("SELECT * from movie where isFavorite = true")
    fun getAllFavoriteMovies(): Flow<List<Movie>>

    //getMovieByID
    @Query("SELECT * from movie where id =:movieId")
    fun getAllMovieById(movieId: String): Flow<List<Movie>>

}