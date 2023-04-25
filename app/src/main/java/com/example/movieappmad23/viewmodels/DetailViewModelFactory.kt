package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad23.repositories.MovieRepository

class DetailViewModelFactory (private val repository: MovieRepository, private val id : String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
                return DetailViewModel(repository = repository,id) as T //never forget as type T
            }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}