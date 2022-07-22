package com.app.yogesh_verma_movie.helper

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.app.yogesh_verma_movie.AppController
import com.app.yogesh_verma_movie.constants.SharedPrefConstants

class SharedPreferencesManager private constructor(context:Context){
    private val preferences:SharedPreferences = context.getSharedPreferences(SharedPrefConstants.PREF_LOGIN_CREDENTIAL,Context.MODE_PRIVATE)

    companion object{
        private var singleton:SharedPreferencesManager?=null

        fun with(context: AppController?):SharedPreferencesManager{
            if (singleton ==null){
                synchronized(lock = SharedPreferencesManager::class.java){
                    if (singleton==null){
                        singleton = Builder(context =context).build()
                    }
                }
            }
            return singleton!!
        }
    }

    private class Builder(context: Context?) {
        private val context: Context

        init {
            if (context == null)
                throw IllegalArgumentException("Context must not be null.")

            this.context = context.applicationContext
        }


        fun build(): SharedPreferencesManager = SharedPreferencesManager(context = context)
    }

    fun getBoolean(key:String,defValue: Boolean):Boolean = preferences.getBoolean(key,defValue)
    fun getString(key: String, defValue: String?): String? = preferences.getString(key, defValue)


    @SuppressLint("CommitPrefEdits")
    fun edit():SharedPreferences.Editor = preferences.edit()




}