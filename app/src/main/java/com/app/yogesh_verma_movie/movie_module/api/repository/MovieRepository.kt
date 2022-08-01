package com.app.yogesh_verma_movie.movie_module.api.repository

import com.app.yogesh_verma_movie.base.BaseActivity
import com.app.yogesh_verma_movie.model.MovieDetailsModel
import com.app.yogesh_verma_movie.model.MovieModel
import com.app.yogesh_verma_movie.movie_module.api.services.MovieApiService

class MovieRepository(
    private val movieApiService: MovieApiService,
    private val baseActivity: BaseActivity?
) : BaseRepository(baseActivity, movieApiService) {

    suspend fun getMovies(showBlockingLoader: Boolean, apiKey: String): MovieModel {
        return doSafeAPIRequest(
            call = { movieApiService.getMovies(apiKey) },
            showBlockingLoader = showBlockingLoader
        )
    }

    suspend fun getUpcomingMovies(showBlockingLoader: Boolean, apiKey: String): MovieModel {
        return doSafeAPIRequest(
            call = { movieApiService.getUpcomingMovies(apiKey) },
            showBlockingLoader = showBlockingLoader
        )
    }

    suspend fun getMoreMovies(showBlockingLoader: Boolean, apiKey: String,page:Int): MovieModel {
        return doSafeAPIRequest(
            call = { movieApiService.getMoreMovies(apiKey,page) },
            showBlockingLoader = showBlockingLoader
        )
    }

    suspend fun getMovieDetails(showBlockingLoader: Boolean,apiKey: String,movieId:Int):MovieDetailsModel{
        return doSafeAPIRequest(
            call = {movieApiService.getMovieDetails(movieId,apiKey)},
            showBlockingLoader = showBlockingLoader
        )
    }



}
