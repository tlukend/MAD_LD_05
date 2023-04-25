package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad23.repositories.MovieRepository


//we have to give parameters we want to give viewmodel to the factory
class MovieViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T { //how the vm is instanced

        //if we are having the right class here, make an instance with this parameter, otherwise cannot cast -> runtimeException
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            return FavoriteViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(AddMovieViewModel::class.java)){
            return AddMovieViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown Viewmodel Class")
    }
}