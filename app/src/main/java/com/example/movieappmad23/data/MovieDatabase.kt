package com.example.movieappmad23.data

import android.content.Context
import androidx.room.*
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.utils.CustomConverter

// in case you change the schema, zB new Column in Movie, increase version number!
@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)


//so that you can use the typeConverterSSSS
@TypeConverters(CustomConverter::class) //übergeben unsere klasse
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao //if i had more daos, i would need to write them here

    companion object{
        @Volatile // because we don't want to cache the value
        private var Instance: MovieDatabase? = null

        fun getDatabase(context: Context) :MovieDatabase {
            return Instance?: synchronized(this){   //define Instance here
                Room.databaseBuilder(context, MovieDatabase::class.java,"movie_db")
                    .fallbackToDestructiveMigration() //whole db gets wiped and newly created with next version
                    .build() // create instance
                    .also{
                        Instance = it  //override
                    }
            }
        }

    }


}