package com.app.yogesh_verma_movie.movie_module.screens.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.base.BaseActivity
import com.app.yogesh_verma_movie.databinding.ActivityMainBinding
import com.app.yogesh_verma_movie.databinding.FragmentMovieBinding
import com.app.yogesh_verma_movie.extensions.addFragmentWithFadeIn
import com.app.yogesh_verma_movie.extensions.addFragmentWithFadeInNoStack
import com.app.yogesh_verma_movie.extensions.removeFragmentFromBottom
import com.app.yogesh_verma_movie.extensions.replaceFragmentFromBottom
import com.app.yogesh_verma_movie.movie_module.screens.fragments.FavouriteFragment
import com.app.yogesh_verma_movie.movie_module.screens.fragments.MovieFragment
import com.app.yogesh_verma_movie.movie_module.screens.fragments.WatchLaterFragment

class MainActivity : BaseActivity() {

    private val _viewBinder by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var movieFragment: MovieFragment? = null
    private var favouriteFragment: FavouriteFragment? = null
    private var watchLaterFragment: WatchLaterFragment? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_viewBinder.root)

        _viewBinder.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bnvMovie ->{
                    supportFragmentManager.replaceFragmentFromBottom(R.id.flMainContainer,movieFragment!!,MovieFragment.TAG)
                }
                R.id.bnvFavourite->{
                    supportFragmentManager.replaceFragmentFromBottom(R.id.flMainContainer,favouriteFragment!!,FavouriteFragment.TAG)

                }

                R.id.bnvWatchLater->{
                    supportFragmentManager.replaceFragmentFromBottom(R.id.flMainContainer,watchLaterFragment!!,WatchLaterFragment.TAG)
                }
            }
            true

        }
    }

    override fun initViewModels() {
        setOnClickListener(_viewBinder.bottomNavigationView)
    }

    override fun initView() {
        movieFragment = MovieFragment.getInstance()
        favouriteFragment = FavouriteFragment.getInstance()
        watchLaterFragment = WatchLaterFragment.getInstance()

        supportFragmentManager.replaceFragmentFromBottom(R.id.flMainContainer,movieFragment!!,MovieFragment.TAG)


    }



    override fun setObservers() {

    }



    override fun setListeners() {


    }

    override fun onViewClick(view: View?) {

    }


}