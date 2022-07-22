package com.app.yogesh_verma_movie.movie_module.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.yogesh_verma_movie.BuildConfig
import com.app.yogesh_verma_movie.base.BaseActivity
import com.app.yogesh_verma_movie.base.BaseFragment
import com.app.yogesh_verma_movie.databinding.FragmentMovieBinding
import com.app.yogesh_verma_movie.model.Results
import com.app.yogesh_verma_movie.movie_module.adapters.MovieAdapter
import com.app.yogesh_verma_movie.movie_module.listeners.OnMovieItemClickListener
import com.app.yogesh_verma_movie.movie_module.viewmodel.MovieViewModel

class MovieFragment : BaseFragment() {


    companion object {
        var TAG = BaseFragment::class.java.simpleName

        fun getInstance():MovieFragment{
            return MovieFragment()
        }
    }

    private var _viewBinder: FragmentMovieBinding? = null
    private var movieList:MutableList<Results> = arrayListOf()
    private lateinit var movieVideoModel:MovieViewModel
    private lateinit var movieAdapter:MovieAdapter
    private var pageCount: Int = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinder = FragmentMovieBinding.inflate(inflater, container, false)
        return _viewBinder?.root
    }

    override fun initViewModels() {
        movieVideoModel = getViewModel(fragment = this,viewModel = MovieViewModel(activity as BaseActivity),className = MovieViewModel::class.java)
        movieVideoModel.getMovies(true,BuildConfig.API_KEY)

    }

    override fun onViewClick(view: View?) {

    }

    override fun initView(view: View) {
        movieAdapter = MovieAdapter()
        _viewBinder!!.rvMovie.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = movieAdapter
            addOnScrollListener(onScrollListener)
        }
        movieAdapter.apply {
            setOnClickListener(onMovieItemClickListener)
        }
    }

    override fun setListeners() {

    }

    override fun setObservers() {
        movieVideoModel.movieListLiveData.observe(this, Observer {
            if (it.results!=null){
                movieAdapter.addItems(it.results!!)
                pageCount = 1
            }
        })

        movieVideoModel.movieMoreListLiveData.observe(this, Observer {
            if (it.results!=null){
                movieAdapter.addItems(it.results!!)
                pageCount++
                _viewBinder?.pbLoadMore?.visibility =View.GONE
            }
        })


    }

    private val onMovieItemClickListener = object : OnMovieItemClickListener{
        override fun onMovieItemClick(results: Results?, position: Int) {
            showToast(results?.title)
        }

    }

    private val onScrollListener =object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!_viewBinder!!.rvMovie.canScrollVertically(1)){
                showToast("Please Wait")
                _viewBinder?.pbLoadMore?.visibility =View.VISIBLE
                movieVideoModel.getMoreMovies(false,BuildConfig.API_KEY,pageCount)

            }
        }
    }





}