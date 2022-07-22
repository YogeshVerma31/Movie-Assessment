package com.app.yogesh_verma_movie.movie_module.api.repository

import com.app.yogesh_verma_movie.base.BaseActivity
import com.app.yogesh_verma_movie.model.MovieModel
import com.app.yogesh_verma_movie.movie_module.api.services.MovieApiService

class MovieRepository(
    private val movieApiService: MovieApiService,
    private val baseActivity: BaseActivity?
) : BaseRepository(baseActivity, movieApiService) {

    suspend fun getMovies(showBlockingLoader: Boolean, apiKey: String): MovieModel? {
        return doSafeAPIRequest(
            call = { movieApiService.getMovies(apiKey) },
            showBlockingLoader = showBlockingLoader
        )
    }

}
