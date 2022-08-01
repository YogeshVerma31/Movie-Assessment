package com.app.yogesh_verma_movie.movie_module.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.databinding.ItemMovieBinding
import com.app.yogesh_verma_movie.model.Results
import com.app.yogesh_verma_movie.movie_module.listeners.OnMovieItemClickListener
import com.app.yogesh_verma_movie.movie_module.viewholders.MovieViewHolder
import com.app.yogesh_verma_movie.movie_module.viewmodel.ItemViewModel

class MultiAdapter<T:ItemViewModel>(@LayoutRes val layoutId:Int):RecyclerView.Adapter<MovieViewHolder<T>>() {
     val list:MutableList<T> = mutableListOf()
     var listFiltered: MutableList<T> = list

    private var onClickListener: Any? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater,
            layoutId, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder<T>, position: Int) {
        val itemViewModel = list[position]
        itemViewModel.adapterPosition = (position+1)
        this.onClickListener?.let { itemViewModel.onClickListener = it }
        holder.bind(itemViewModel)

    }

    override fun getItemCount(): Int {
        return if(list.size <10) list.size else 10
    }

    fun getItems():MutableList<T>{
        return list as MutableList<T>
    }

    fun addItems(itemList: MutableList<T>){
        addItems(itemList,true)
    }

    fun setOnClickListener(onClickListener:Any?){
        this.onClickListener = onClickListener
    }


    fun addItems(itemList: MutableList<T>,notifyDataSetChanged:Boolean){
        val start = list.size
        list.addAll(itemList)
        if(notifyDataSetChanged)
            notifyItemRangeInserted(start,itemList.size)

    }
}