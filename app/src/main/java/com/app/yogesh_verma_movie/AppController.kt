package com.app.yogesh_verma_movie

import android.app.Application
import com.app.yogesh_verma_movie.movie_module.api.services.BaseCloudAPIService
import retrofit2.Retrofit

class AppController:Application() {
    companion object{
        val TAG:String = AppController::class.java.simpleName
        var appController:AppController? = null
        var cloudApiService: Retrofit? = null
    }

    override fun onCreate() {
        super.onCreate()
        appController = this
        cloudApiService = BaseCloudAPIService()

    }
}