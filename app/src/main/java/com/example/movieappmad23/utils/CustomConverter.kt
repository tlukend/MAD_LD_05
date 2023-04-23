package com.example.movieappmad23.utils

import androidx.room.TypeConverter
import com.example.movieappmad23.models.Genre

class CustomConverter {

    @TypeConverter
    //from genrelist into string for db
    fun genreListToString(value: List<Genre>): String {
        return value.joinToString(",")
    }


    //from string to genre from db
    @TypeConverter
    fun stringToGenreList (value: String): List<Genre> {
        val genreList = mutableListOf<Genre>()

        value.split(",").map{it.trim()}.forEach{
            genreList.add(enumValueOf(it))
        }
        return genreList
    }


    //turn list intro string for db
    @TypeConverter
    fun listToString(value: List<String>) = value.joinToString { "," }


    //turn string into list from db
    @TypeConverter
    fun stringToList(value: String) = value.split(",").map {it.trim()}
}