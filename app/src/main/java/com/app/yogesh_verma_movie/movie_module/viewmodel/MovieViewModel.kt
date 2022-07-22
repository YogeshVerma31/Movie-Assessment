package com.app.yogesh_verma_movie.movie_module.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.yogesh_verma_movie.base.BaseActivity
import com.app.yogesh_verma_movie.model.MovieModel
import com.app.yogesh_verma_movie.movie_module.api.repository.MovieRepository
import com.app.yogesh_verma_movie.movie_module.api.services.BaseCloudAPIService
import com.app.yogesh_verma_movie.movie_module.api.services.MovieApiService
import kotlinx.coroutines.launch

class MovieViewModel(baseActivity: BaseActivity?):BaseViewModel(){

    private val movieRepository = MovieRepository(BaseCloudAPIService.getApiService(MovieApiService::class.java),baseActivity)

    internal val movieListLiveData = MutableLiveData<MovieModel>()

    fun getMovies(showBlockingLoader:Boolean,apiKey:String){
        scope.launch {
            try{
                movieListLiveData.postValue(movieRepository.getMovies(showBlockingLoader,apiKey))
            }catch (e:Throwable){

            }
        }
    }


}