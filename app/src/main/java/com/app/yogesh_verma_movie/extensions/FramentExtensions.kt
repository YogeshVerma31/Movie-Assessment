package com.app.yogesh_verma_movie.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.yogesh_verma_movie.R

fun FragmentManager.addFragmentWithFadeIn(containerViewId: Int, fragment: Fragment, tag: String?) {
    this.beginTransaction()
        .addToBackStack(tag)
        .setCustomAnimations(R.anim.anim_fade_in, R.anim.anim_fade_in, R.anim.anim_fade_out, R.anim.anim_fade_out)
        .add(containerViewId, fragment, tag)
        .commitAllowingStateLoss()
}


fun FragmentManager.addFragmentWithFadeInNoStack(containerViewId: Int, fragment: Fragment) {
    this.beginTransaction()
        .setCustomAnimations(R.anim.anim_fade_in, R.anim.anim_fade_in, R.anim.anim_fade_out, R.anim.anim_fade_out)
        .add(containerViewId, fragment)
        .commitAllowingStateLoss()
}

fun FragmentManager.replaceFragmentFromBottom(containerViewId: Int,fragment: Fragment,tag: String) {
    this.beginTransaction()
        .setCustomAnimations(R.anim.bottom_in,R.anim.bottom_out)
        .replace(containerViewId,fragment,tag)
        .commitAllowingStateLoss()
}

fun FragmentManager.removeFragmentFromBottom(fragment: Fragment) {
    this.beginTransaction()
        .setCustomAnimations(R.anim.bottom_in, R.anim.bottom_out)
        .remove(fragment)
        .commitAllowingStateLoss()

}