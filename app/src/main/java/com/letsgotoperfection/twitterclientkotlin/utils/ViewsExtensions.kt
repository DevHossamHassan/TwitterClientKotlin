package com.letsgotoperfection.twitterclientkotlin.utils

import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide


/**
 * @author hossam.
 */
fun View.show() {
    if (isVisible()) return
    this.visibility = View.VISIBLE
}

fun View.hide() {
    if (!isVisible()) return
    this.visibility = View.GONE
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun SwipeRefreshLayout.showLoadingView() {
    isRefreshing = true
}

fun SwipeRefreshLayout.hideLoadingView() {
    isRefreshing = false
}


fun ImageView.loadUrl(url: String) {
    Glide.with(context.applicationContext)
            //getting higher image's quality
            .load(url.replace("_normal", "_400x400"))
            .into(this)
}


