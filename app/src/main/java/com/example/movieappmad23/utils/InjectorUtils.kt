package com.example.movieappmad23.utils

import android.content.Context
import com.example.movieappmad23.data.MovieDatabase
import com.example.movieappmad23.repositories.MovieRepository
import com.example.movieappmad23.viewmodels.DetailViewModel
import com.example.movieappmad23.viewmodels.DetailViewModelFactory
import com.example.movieappmad23.viewmodels.MovieViewModelFactory


//made to make instancing for more viewmodels easier, no need to write all those lines
object InjectorUtils {
    //makes function below easier, only a function to create a repo
    private fun getMovieRepository(context: Context): MovieRepository{ //still need context as param.
        return MovieRepository(MovieDatabase.getDatabase(context).movieDao()) //takes the dao from above declared db
    }
    private fun provideDetailViewModelFactory(context: Context, id: String): DetailViewModelFactory{ //erstellen factory, die ID braucht
        val repository = getMovieRepository(context)
        return DetailViewModelFactory(repository, id)
    }
    // liefert factory zurück,
    fun provideMovieViewModelFactory(context: Context): MovieViewModelFactory{ //context übergeben wir außerhalb
        val repository = getMovieRepository(context)
        return MovieViewModelFactory(repository)
    }

}