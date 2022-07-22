package com.app.yogesh_verma_movie.movie_module.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

open class BaseViewModel():ViewModel() {
    companion object {
        val TAG: String = BaseViewModel::class.java.simpleName
    }

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO

    protected val scope = CoroutineScope(coroutineContext)


    fun cancelAllPendingRequest() = coroutineContext.cancel()
}