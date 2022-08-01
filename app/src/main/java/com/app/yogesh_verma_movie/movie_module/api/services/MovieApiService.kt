package com.app.yogesh_verma_movie.movie_module.api.services

import com.app.yogesh_verma_movie.constants.BaseAPIConstants
import com.app.yogesh_verma_movie.model.MovieDetailsModel

import com.app.yogesh_verma_movie.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService:BaseApiService {

    @GET(BaseAPIConstants.API_MOVIES)
    suspend fun getMovies(@Query("api_key") api_key:String):Response<MovieModel>

    @GET(BaseAPIConstants.API_UPCOMING_MOVIES)
    suspend fun getUpcomingMovies(@Query("api_key") api_key:String):Response<MovieModel>

    @GET(BaseAPIConstants.API_MOVIES)
    suspend fun getMoreMovies(@Query("api_key") api_key:String,@Query("page") page:Int):Response<MovieModel>

    @GET(BaseAPIConstants.API_MOVIE_DEATILS)
    suspend fun getMovieDetails(@Path("movieId") movieId:Int,@Query("api_key") api_key: String):Response<MovieDetailsModel>



}