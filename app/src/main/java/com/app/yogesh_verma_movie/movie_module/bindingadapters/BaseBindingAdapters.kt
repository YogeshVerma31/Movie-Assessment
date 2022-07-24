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
        view?.loadImage(url,R.drawable.notavailable)
    }

    @BindingAdapter("hourMinute")
    @JvmStatic
    fun hourMinute(view: TextView?, time: Int) {
        var hours = time.div(60)
        var minute = time.mod(60)
        view?.text = "$hours h $minute m"

    }


}