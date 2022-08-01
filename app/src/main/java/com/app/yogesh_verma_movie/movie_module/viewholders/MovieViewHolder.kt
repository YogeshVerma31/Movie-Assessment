package com.app.yogesh_verma_movie.movie_module.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.app.yogesh_verma_movie.BR
import com.app.yogesh_verma_movie.databinding.ItemMovieBinding
import com.app.yogesh_verma_movie.movie_module.viewmodel.ItemViewModel

class MovieViewHolder<T:ItemViewModel>(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(itemViewModel: T){
        binding.setVariable(BR.movieModel,itemViewModel)
        binding.setVariable(BR.position,itemViewModel.adapterPosition)
        binding.setVariable(BR.onClickListener,itemViewModel.onClickListener)
        binding.executePendingBindings()
    }
}
