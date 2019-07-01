package com.varseno.myapplication.utils

import android.app.Fragment
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity

/**
 * Created by Sudam Chole on 7/1/2019.
 */
fun AppCompatActivity.replaceFragmenty(fragment: Fragment,
                                       allowStateLoss: Boolean = false,
                                       @IdRes containerViewId: Int) {
    val ft = fragmentManager
        .beginTransaction()
        .replace(containerViewId, fragment)
    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    } else if (allowStateLoss) {
        ft.commitAllowingStateLoss()
    }
}