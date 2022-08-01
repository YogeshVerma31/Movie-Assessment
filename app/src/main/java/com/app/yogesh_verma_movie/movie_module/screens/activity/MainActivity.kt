package com.app.yogesh_verma_movie.movie_module.screens.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.base.BaseActivity
import com.app.yogesh_verma_movie.databinding.ActivityMainBinding
import com.app.yogesh_verma_movie.databinding.FragmentMovieBinding
import com.app.yogesh_verma_movie.extensions.addFragmentWithFadeIn
import com.app.yogesh_verma_movie.extensions.addFragmentWithFadeInNoStack
import com.app.yogesh_verma_movie.extensions.removeFragmentFromBottom
import com.app.yogesh_verma_movie.extensions.replaceFragmentFromBottom
import com.app.yogesh_verma_movie.model.Results
import com.app.yogesh_verma_movie.movie_module.screens.fragments.FavouriteFragment
import com.app.yogesh_verma_movie.movie_module.screens.fragments.MovieDetailFragment
import com.app.yogesh_verma_movie.movie_module.screens.fragments.MovieFragment
import com.app.yogesh_verma_movie.movie_module.screens.fragments.WatchLaterFragment
import com.app.yogesh_verma_movie.screens.fragment.HomeFragment

class MainActivity : BaseActivity() {

    private val _viewBinder by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var movieFragment: MovieFragment? = null
    private var favouriteFragment: FavouriteFragment? = null
    private var watchLaterFragment: WatchLaterFragment? = null
    private var homeFragment: HomeFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_viewBinder.root)

        _viewBinder.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bnvMovie -> {
                    supportFragmentManager.replaceFragmentFromBottom(
                        R.id.flBottomContainer,
                        movieFragment!!,
                        MovieFragment.TAG
                    )
                }
                R.id.bnvFavourite -> {
                    supportFragmentManager.replaceFragmentFromBottom(
                        R.id.flBottomContainer,
                        favouriteFragment!!,
                        FavouriteFragment.TAG
                    )

                }

                R.id.bnvWatchLater -> {
                    supportFragmentManager.replaceFragmentFromBottom(
                        R.id.flBottomContainer,
                        watchLaterFragment!!,
                        WatchLaterFragment.TAG
                    )
                }
            }
            true

        }
    }

    override fun initViewModels() {
    }

    override fun initView() {
        movieFragment = MovieFragment.getInstance()
        favouriteFragment = FavouriteFragment.getInstance()
        watchLaterFragment = WatchLaterFragment.getInstance()
        homeFragment = HomeFragment.getInstance()

        supportFragmentManager.replaceFragmentFromBottom(
            R.id.flBottomContainer,
            movieFragment!!,
            MovieFragment.TAG
        )


    }


    override fun setObservers() {

    }


    override fun setListeners() {
        setOnClickListener(_viewBinder.cvSearch)
    }


    override fun onViewClick(view: View?) {
        when (view?.id) {
            R.id.cvSearch -> apply {
                addFragment(R.id.flHomeContainer, homeFragment)
                _viewBinder.bottomNavigationView.visibility = View.GONE
                _viewBinder.cvSearch.visibility = View.GONE
            }
        }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count>0){
            if(!_viewBinder.bottomNavigationView.isVisible){
                _viewBinder.bottomNavigationView.visibility = View.VISIBLE
                _viewBinder.cvSearch.visibility = View.VISIBLE
            }
            supportFragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }
        Log.d("TAG","${count}")
    }


    private fun addFragment(frameLayout: Int, fragment: Fragment?) {
        supportFragmentManager.addFragmentWithFadeIn(frameLayout, fragment!!, HomeFragment.TAG)
    }


}