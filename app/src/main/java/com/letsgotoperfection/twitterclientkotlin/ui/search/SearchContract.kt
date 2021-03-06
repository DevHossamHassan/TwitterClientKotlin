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
        fun hideSwipeToRefreshProgressBar()
        fun showSwipeToRefreshProgressBar()
        fun navigateToDetailsFragment(tweet: Statuse)
    }

    interface Presenter : BaseContract.Presenter {
        fun getExistedTweets(): List<Statuse>
        fun getTweetsCount(): Int
        fun onQueryChanged(query: String)
        fun onLoadMore(query: String)
        fun destroy()
    }
}