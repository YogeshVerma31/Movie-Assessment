package com.app.yogesh_verma_movie.movie_module.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.extensions.loadImage

object BaseBindingAdapters {

    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(view: ImageView?, url: String?) {
        view?.loadImage(url)
    }

    @BindingAdapter("setTextUserScore")
    @JvmStatic
    fun setTextUserScore(view: TextView?,userScore:Int?){
        view?.text = "${userScore?.times(10)}%"
    }
}