package com.app.yogesh_verma_movie.base

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.pm.ActivityInfo
import android.content.res.AssetManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.app.yogesh_verma_movie.R


abstract class BaseActivity:AppCompatActivity() {

    companion object{
        var TAG:String =BaseActivity::class.java.simpleName
    }

    private var toastMessage:Toast?=null
    private var loaderAlertDialog:AlertDialog? = null


    abstract fun initViewModels()
    abstract fun initView()
    abstract fun setObservers()
    abstract fun setListeners()
    abstract fun onViewClick(view: View?)

    private var permsList = arrayOf<String>(
        Manifest.permission.READ_CALL_LOG,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.CALL_PHONE
    )


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        addOverrideAnimationFinish()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            permsList += arrayOf<String>(Manifest.permission.ANSWER_PHONE_CALLS)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        initViewModels()
        initView()
        setObservers()
        setListeners()
    }



    private fun initBlockingLoaderDialog(){
        loaderAlertDialog = AlertDialog.Builder(this@BaseActivity).apply {
            setView(LayoutInflater.from(context).inflate(R.layout.layout_blocking_loader, null))
            setCancelable(true)
        }.create()

        loaderAlertDialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }


    override fun getAssets(): AssetManager {
        return resources.assets
    }

    private val onClickListener = View.OnClickListener { view ->
        onViewClick(view)
    }

    fun setOnClickListener(view: View?){
        view?.setOnClickListener(onClickListener)
    }

    private fun cancelToastMessage() {
        toastMessage?.cancel()
    }

    fun showToast(message:String?){
        if (message != null) {
            cancelToastMessage()
            if (applicationContext != null) {
                toastMessage = Toast.makeText(applicationContext, message, Toast.LENGTH_LONG)
                toastMessage?.show()
            }
        }
    }

    override fun finish() {
        super.finish()
        addOverrideAnimationFinish()
    }

    fun showBlockingLoader(){
        runOnUiThread {
            if(loaderAlertDialog==null){
                initBlockingLoaderDialog()
            }
            loaderAlertDialog?.show()
        }
    }

    fun hideBlockingLoader(){
        runOnUiThread {
            if(loaderAlertDialog?.isShowing==true){
                loaderAlertDialog?.dismiss()
            }
        }
    }

    private fun addOverrideAnimationFinish(){
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    private fun addOverrideAnimationOnStart(){
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }




}