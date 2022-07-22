package com.app.yogesh_verma_movie.movie_module.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.yogesh_verma_movie.extensions.loadImage

object BaseBindingAdapters {

    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(view: ImageView?, url: String?) {
        view?.loadImage(url)
    }
}