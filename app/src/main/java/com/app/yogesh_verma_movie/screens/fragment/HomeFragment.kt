package com.app.yogesh_verma_movie.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.base.BaseFragment
import com.app.yogesh_verma_movie.databinding.FragmentHomeBinding
import com.app.yogesh_verma_movie.databinding.FragmentMovieBinding
import com.app.yogesh_verma_movie.movie_module.screens.fragments.MovieFragment


class HomeFragment : BaseFragment() {
    companion object {
        var TAG = BaseFragment::class.java.simpleName

        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private var _viewBinder: FragmentHomeBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinder = FragmentHomeBinding.inflate(inflater, container, false)
        return _viewBinder?.root
    }

    override fun initViewModels() {

    }

    override fun onViewClick(view: View?) {

    }

    override fun initView(view: View) {

    }

    override fun setListeners() {

    }

    override fun setObservers() {

    }

}