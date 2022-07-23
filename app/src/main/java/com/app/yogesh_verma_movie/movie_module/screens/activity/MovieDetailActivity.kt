package com.app.yogesh_verma_movie.movie_module.screens.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.base.BaseActivity
import com.app.yogesh_verma_movie.constants.BaseAPIConstants
import com.app.yogesh_verma_movie.databinding.ActivityMainBinding
import com.app.yogesh_verma_movie.databinding.ActivityMovieDetailActivityBinding
import com.app.yogesh_verma_movie.extensions.addFragmentWithFadeIn
import com.app.yogesh_verma_movie.extensions.addFragmentWithFadeInNoStack
import com.app.yogesh_verma_movie.extensions.replaceFragmentFromBottom
import com.app.yogesh_verma_movie.generated.callback.OnClickListener
import com.app.yogesh_verma_movie.model.Results
import com.app.yogesh_verma_movie.movie_module.screens.fragments.MovieDetailFragment
import com.app.yogesh_verma_movie.movie_module.screens.fragments.MovieFragment

class MovieDetailActivity : BaseActivity() {

    private val _viewBinder by lazy { ActivityMovieDetailActivityBinding.inflate(layoutInflater) }

    private var movieDetailFragment: MovieDetailFragment? = null

    companion object{
        fun getIntent(context:Context?,resultModel:Results){
            val bundle = Bundle()
            bundle.putSerializable(BaseAPIConstants.API_MOVIE_DEATILS,resultModel)
            val intent = Intent(context,MovieDetailActivity::class.java)
            intent.putExtras(bundle)
            context?.startActivity(intent)
        }
    }


    private val resultModel:Results
        get(){
            return intent.getSerializableExtra(BaseAPIConstants.API_MOVIE_DEATILS) as Results
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_viewBinder.root)
    }

    private fun setUpToolbar(){
        setSupportActionBar(_viewBinder.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        _viewBinder.toolBar.apply {
            title = resultModel.title
        }

    }



    override fun initViewModels() {

    }

    override fun initView() {
        setUpToolbar()
        movieDetailFragment = MovieDetailFragment.getInstance()
        movieDetailFragment?.arguments =MovieDetailFragment.getBundle(resultModel)
        supportFragmentManager.addFragmentWithFadeInNoStack(R.id.flMovieDetails,movieDetailFragment!!)
    }



    override fun setObservers() {

    }

    override fun setListeners() {
        _viewBinder.toolBar.setNavigationOnClickListener(onNavigationClick)
    }

    override fun onViewClick(view: View?) {

    }

    private val onNavigationClick = object : View.OnClickListener{
        override fun onClick(v: View?) {
            onBackPressed()
        }

    }


}