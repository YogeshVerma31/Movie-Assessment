package com.app.yogesh_verma_movie.customviews

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.databinding.LayoutCustomEditTextBinding

class CustomEditText:FrameLayout {




    private var binding: LayoutCustomEditTextBinding? = null

    init {
        binding = LayoutCustomEditTextBinding.inflate(LayoutInflater.from(context), this, true)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initViews(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initViews(attrs)
    }

    private fun initViews(attrs: AttributeSet) {
        LayoutCustomEditTextBinding.inflate(LayoutInflater.from(context))

        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomEditText, 0, 0).apply {
            binding?.apply {
                tvLabel.text = getString(R.styleable.CustomEditText_setLabel)
                etInput.hint = getString(R.styleable.CustomEditText_setHint)
                etInput.inputType =
                    getInt(R.styleable.CustomEditText_android_inputType, InputType.TYPE_NULL)
                etInput.isEnabled = getBoolean(R.styleable.CustomEditText_isEnabled, true)
                etInput.imeOptions = getInt(
                    R.styleable.CustomEditText_android_imeOptions,
                    EditorInfo.IME_ACTION_NEXT
                )
            }
        }

    }
        fun setError(error:String){
            binding?.apply {
                tvError.text = error
            }
        }

        fun setText(mText:String){
            binding?.apply {
                etInput.setText(mText)
            }
        }

        fun getText():String{
                return binding?.etInput?.text.toString()
        }

}