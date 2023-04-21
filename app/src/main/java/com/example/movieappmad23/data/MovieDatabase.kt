package com.example.movieappmad23.data

import android.content.Context
import androidx.room.*
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.utils.CustomConverters

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(CustomConverters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var Instance: MovieDatabase? = null

        fun getDatabase(context: Context) :MovieDatabase {
            return Instance?: synchronized(this){
                Room.databaseBuilder(context, MovieDatabase::class.java,"movie_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also{
                        Instance = it
                    }
            }
        }

    }


}