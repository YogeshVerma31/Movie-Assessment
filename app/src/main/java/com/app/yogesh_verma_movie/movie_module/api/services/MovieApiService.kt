package com.app.yogesh_verma_movie.movie_module.api.services

import com.app.yogesh_verma_movie.constants.BaseAPIConstants

import com.app.yogesh_verma_movie.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApiService:BaseApiService {

    @Headers(BaseAPIConstants.CONTENT_TYPE_JSON)
    @GET(BaseAPIConstants.API_MOVIES)
    suspend fun getMovies(@Query("api_key") api_key:String):Response<MovieModel>

}