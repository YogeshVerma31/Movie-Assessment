package com.app.yogesh_verma_movie.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.app.yogesh_verma_movie.movie_module.api.services.BaseViewModelFactory

abstract class BaseFragment: Fragment() {
    companion object{
        var TAG = BaseFragment::class.java.simpleName
    }

    protected val _activity by lazy { context as BaseActivity }

    abstract fun initViewModels()
    abstract fun onViewClick(view: View?)
    abstract fun initView(view: View)
    abstract fun setListeners()
    abstract fun setObservers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModels()
        initView(view)
        setListeners()
        setObservers()
    }



    protected fun setOnClickListener(view: View?){
        view?.setOnClickListener(onClickListener)
    }

    protected val onClickListener = View.OnClickListener { view ->
        onViewClick(view)
    }

    protected fun showBlockingLoader(){
        if(activity is BaseActivity){
            (activity as BaseActivity).showBlockingLoader()
        }
    }

    protected fun hideBlockingLoader(){
        if(activity is BaseActivity){
            (activity as BaseActivity).hideBlockingLoader()
        }
    }


    protected fun showToast(message: String?){
        if(activity is BaseActivity) {
            (activity as BaseActivity).showToast(message)
        }
    }

    protected fun<T: ViewModel> getViewModel(fragment:Fragment, viewModel: ViewModel, className:Class<T>): T {
        return ViewModelProviders.of(fragment, BaseViewModelFactory(viewModel,className)).get(className)
    }


}