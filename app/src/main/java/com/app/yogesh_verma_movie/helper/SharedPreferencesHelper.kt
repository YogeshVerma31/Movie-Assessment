package com.app.yogesh_verma_movie.helper

import com.app.yogesh_verma_movie.AppController

object SharedPreferencesHelper {

    fun getBoolean(key:String):Boolean{
    return getBoolean(key,false)
    }

    fun getBoolean(key:String,defValue: Boolean):Boolean{
        return SharedPreferencesManager.with(context = AppController.appController).getBoolean(key, defValue)
    }

    fun getString(key:String):String?{
        return SharedPreferencesManager.with(context = AppController.appController).getString(key, null)
    }
    fun putString(key: String, value:String?){
        SharedPreferencesManager.with(context = AppController.appController).edit().putString(key, value).apply()
    }

    fun clearLocalInfo(){
        SharedPreferencesManager.with(context = AppController.appController).edit().apply{
            clear()
        }.apply()
    }
}