package com.app.yogesh_verma_movie.movie_module.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.yogesh_verma_movie.base.BaseActivity
import com.app.yogesh_verma_movie.model.MovieDetailsModel
import com.app.yogesh_verma_movie.model.MovieModel
import com.app.yogesh_verma_movie.movie_module.api.repository.MovieRepository
import com.app.yogesh_verma_movie.movie_module.api.services.BaseCloudAPIService
import com.app.yogesh_verma_movie.movie_module.api.services.MovieApiService
import kotlinx.coroutines.launch

class MovieViewModel(baseActivity: BaseActivity?):BaseViewModel(){

    private val movieRepository = MovieRepository(BaseCloudAPIService.getApiService(MovieApiService::class.java),baseActivity)

    internal val movieListLiveData = MutableLiveData<MovieModel>()
    internal val movieMoreListLiveData = MutableLiveData<MovieModel>()
    internal val movieUpcomingListLiveData = MutableLiveData<MovieModel>()
    internal val movieDetailsListLiveData = MutableLiveData<MovieDetailsModel>()

    fun getMovies(showBlockingLoader:Boolean,apiKey:String){
        scope.launch {
            try{
                movieListLiveData.postValue(movieRepository.getMovies(showBlockingLoader,apiKey))
            }catch (e:Throwable){
                e.printStackTrace()
            }
        }
    }fun getUpcomingMovies(showBlockingLoader:Boolean,apiKey:String){
        scope.launch {
            try{
                movieUpcomingListLiveData.postValue(movieRepository.getUpcomingMovies(showBlockingLoader,apiKey))
            }catch (e:Throwable){
                e.printStackTrace()
            }
        }
    }

    fun getMoreMovies(showBlockingLoader:Boolean,apiKey:String,page:Int){
        scope.launch {
            try{
                movieMoreListLiveData.postValue(movieRepository.getMoreMovies(showBlockingLoader,apiKey,page))
            }catch (e:Throwable){
                e.printStackTrace()
            }
        }
    }

    fun getMovieDetails(showBlockingLoader: Boolean,apiKey: String,movieId:Int){
        scope.launch {
            try {
                movieDetailsListLiveData.postValue(movieRepository.getMovieDetails(showBlockingLoader,apiKey,movieId))
            }catch (e:Throwable){
                e.printStackTrace()
            }
        }
    }


}