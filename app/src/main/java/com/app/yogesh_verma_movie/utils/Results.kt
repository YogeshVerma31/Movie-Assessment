package com.app.yogesh_verma_movie.utils


sealed class Results<out T: Any> {
    data class Success<out T : Any>(val data: T) : Results<T>()
    data class Error(val error: String?) : Results<Nothing>()
}