package com.app.yogesh_verma_movie.extensions

import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.constants.BaseAPIConstants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url:String?,placeholder: Int){
    if (url!=null){
        Glide.with(context)
            .load(BaseAPIConstants.BASE_IMAGE_URL+url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.notavailable)
            ).into(this)
    }
}

fun ImageView.loadImage(url: String?) {
    if (url != null) {
        Glide.with(context)
            .load(BaseAPIConstants.BASE_IMAGE_URL+url)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}
