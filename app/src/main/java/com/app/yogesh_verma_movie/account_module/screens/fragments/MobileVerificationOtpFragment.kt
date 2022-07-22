package com.app.yogesh_verma_movie.account_module.screens.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.yogesh_verma_movie.movie_module.screens.activity.MainActivity
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.base.BaseFragment
import com.app.yogesh_verma_movie.constants.BaseAppConstants
import com.app.yogesh_verma_movie.constants.SharedPrefConstants
import com.app.yogesh_verma_movie.databinding.FragmentMobileVerificationOtpBinding
import com.app.yogesh_verma_movie.helper.LogHelper
import com.app.yogesh_verma_movie.helper.SharedPreferencesHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class MobileVerificationOtpFragment : BaseFragment() {

    companion object {
        var TAG = BaseFragment::class.java.simpleName

        fun getInstance(verificationId:String,mobileNumber:String):MobileVerificationOtpFragment{
            return MobileVerificationOtpFragment().apply {
                arguments = Bundle().apply {
                    putString(BaseAppConstants.PHONE_AUTH_VERIFICATION_ID,verificationId)
                    putString(BaseAppConstants.KEY_MOBILE_NUMBER,mobileNumber)
                }
            }
        }
    }

    private var _viewBinder: FragmentMobileVerificationOtpBinding? = null

    private var mobileNumber:String? = null
        get(){
            return arguments?.getString(BaseAppConstants.KEY_MOBILE_NUMBER)
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinder = FragmentMobileVerificationOtpBinding.inflate(inflater,container,false)
        return _viewBinder?.root
    }

    override fun initViewModels() {

    }

    override fun onViewClick(view: View?) {
        when(view?.id){
            R.id.btnVerifyOtp -> {
                doMobileVerification()
            }
        }
    }

    override fun initView(view: View) {
        _viewBinder?.tvVerificationCodeSentTo?.text = getString(R.string.text_verification_code_sent_to,mobileNumber)

    }

    override fun setListeners() {
        setOnClickListener(_viewBinder?.btnVerifyOtp)
    }

    override fun setObservers() {

    }

    private fun doMobileVerification(){
        showToast("Hi Again")
        if (!_viewBinder?.otpView?.otp.toString().isNullOrEmpty()){
            val verificationId:String? =arguments?.getString(BaseAppConstants.PHONE_AUTH_VERIFICATION_ID)
            if (verificationId!=null){
                showBlockingLoader()
                val credential =PhoneAuthProvider.getCredential(verificationId,_viewBinder?.otpView?.otp?.toString()!!)
                signInWithPhoneAuthCredential(credential)
                LogHelper.debug(TAG,"Credentials - $verificationId")
            }
        }

    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(activity!!) { task ->
                task.result.user?.getIdToken(true)?.addOnCompleteListener {
                    if (task.isSuccessful) {
                        hideBlockingLoader()
                        val idToken: String? = it.result.token
                        LogHelper.debug(TAG,"idtoken - "+idToken)
                        SharedPreferencesHelper.putString(SharedPrefConstants.ACCESS_TOKEN,idToken)
                        gotoHomeActivity()
                    }else{
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            hideBlockingLoader()
                            showToast(task.exception?.localizedMessage)
                        }
                    }
                }
            }
    }

    private fun gotoHomeActivity(){
        if(activity!=null){
            startActivity(Intent(context, MainActivity::class.java))
            activity?.finish()
        }
    }

}