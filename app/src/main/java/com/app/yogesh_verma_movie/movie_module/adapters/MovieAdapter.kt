package com.app.yogesh_verma_movie.movie_module.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.databinding.ItemMovieBinding
import com.app.yogesh_verma_movie.model.Results
import com.app.yogesh_verma_movie.movie_module.listeners.OnMovieItemClickListener
import com.app.yogesh_verma_movie.movie_module.viewholders.MovieViewHolder

class MovieAdapter:RecyclerView.Adapter<MovieViewHolder>() {
     val movieList:MutableList<Results> = mutableListOf()
     var movieListFiltered: MutableList<Results> = movieList

    private var onClickListener: Any? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.item_movie, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.movieModel = movieList.get(position)
        holder.binding.onClickListener = onClickListener as OnMovieItemClickListener?
        holder.binding.position = position
    }

    override fun getItemCount(): Int {
        return if(movieList.size <10) movieList.size else 10
    }

    fun getItems():MutableList<Results>{
        return movieList as MutableList<Results>
    }

    fun addItems(itemList: MutableList<Results>){
        addItems(itemList,true)
    }

    fun setOnClickListener(onClickListener:Any?){
        this.onClickListener = onClickListener
    }


    fun addItems(itemList: MutableList<Results>,notifyDataSetChanged:Boolean){
        val start = movieList.size
        movieList.addAll(itemList)
        if(notifyDataSetChanged)
            notifyItemRangeInserted(start,itemList.size)

    }
}