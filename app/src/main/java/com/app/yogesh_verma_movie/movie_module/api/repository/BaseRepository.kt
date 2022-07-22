package com.app.yogesh_verma_movie.movie_module.api.repository

import com.app.yogesh_verma_movie.base.BaseActivity
import com.app.yogesh_verma_movie.movie_module.api.services.BaseApiService
import com.app.yogesh_verma_movie.utils.Results
import retrofit2.Response

open class BaseRepository(private val baseActivity: BaseActivity?,private val baseApiService: BaseApiService?) {
    suspend fun <T : Any> doSafeAPIRequest(call: suspend () -> Response<T>, showBlockingLoader:Boolean): T {
        val result : Results<T> = returnSafeAPIResponse(call,baseActivity,showBlockingLoader)
        var data : T? = null

        when(result) {
            is Results.Success ->
                data = result.data
            is Results.Error -> {
                throw Exception(result.error ?: "")
            }
        }
        return data
    }

    suspend fun <T : Any> doSafeAPIRequest(call: suspend () -> Response<T>): T? {
        return doSafeAPIRequest(call,false)
    }

    private suspend fun <T: Any> returnSafeAPIResponse(call: suspend ()-> Response<T>,baseActivity: BaseActivity?,showBlockingLoader:Boolean) : Results<T> {
        if(showBlockingLoader){
            baseActivity?.showBlockingLoader()
        }

        try{
            val response = call.invoke()
            if(response.isSuccessful) {
                baseActivity?.hideBlockingLoader()
                return Results.Success(response.body()!!)
            }

            baseActivity?.hideBlockingLoader()

            return Results.Error(error = response.errorBody()?.string().toString())
        }catch (e:Exception){
            baseActivity?.hideBlockingLoader()
            return Results.Error(e.localizedMessage)
        }
    }

}