package com.letsgotoperfection.twitterclientkotlin.ui.search

import android.app.Fragment
import com.letsgotoperfection.twitterclientkotlin.models.Statuse
import com.letsgotoperfection.twitterclientkotlin.ui.base.BaseContract


/**
 * @author hossam.
 */
class SearchContract : BaseContract {

    interface View : BaseContract.View<Fragment> {
        fun showToast(msg: String)
        fun updateDate()
        fun updateInsertedData(itemCount: Int)
        fun showProgressBar()
        fun hideProgressBar()
        fun hideSwipeToRefreshProgressBar()
        fun showSwipeToRefreshProgressBar()
    }

    interface Presenter : BaseContract.Presenter {
        fun getExistedTracks(): List<Statuse>
        fun getTracksListSize(): Int
        fun onLoadMore(query: String)
        fun destroy()
    }
}