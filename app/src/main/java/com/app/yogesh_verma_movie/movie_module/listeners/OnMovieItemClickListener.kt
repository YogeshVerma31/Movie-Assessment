package com.app.yogesh_verma_movie.movie_module.listeners

import com.app.yogesh_verma_movie.model.Results

interface OnMovieItemClickListener {
    fun onMovieItemClick(results: Results?,position:Int)
}