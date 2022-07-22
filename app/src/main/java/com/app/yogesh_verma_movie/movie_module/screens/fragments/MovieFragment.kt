package com.app.yogesh_verma_movie.movie_module.screens.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.app.yogesh_verma_movie.BuildConfig
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.base.BaseActivity
import com.app.yogesh_verma_movie.base.BaseFragment
import com.app.yogesh_verma_movie.databinding.FragmentMovieBinding
import com.app.yogesh_verma_movie.model.results
import com.app.yogesh_verma_movie.movie_module.viewmodel.MovieViewModel

class MovieFragment : BaseFragment() {

    private lateinit var movieVideoModel:MovieViewModel


    companion object {
        var TAG = BaseFragment::class.java.simpleName

        fun getInstance():MovieFragment{
            return MovieFragment()
        }
    }

    private var _viewBinder: FragmentMovieBinding? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

    }

    override fun setListeners() {

    }

    override fun setObservers() {
        movieVideoModel.movieListLiveData.observe(this, Observer {
            _viewBinder?.tv?.text = it.page.toString()
        })

    }
}