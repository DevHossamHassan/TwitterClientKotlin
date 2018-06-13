package com.letsgotoperfection.twitterclientkotlin.ui.search

import RetrofitProvider
import android.annotation.SuppressLint
import android.util.Log
import com.letsgotoperfection.twitterclientkotlin.models.Statuse
import com.letsgotoperfection.twitterclientkotlin.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author hossam.
 */
class SearchPresenter(private val searchView: SearchContract.View)
    : BasePresenter<SearchContract.View>(searchView), SearchContract.Presenter {

    override fun getExistedTweets(): List<Statuse> {
        return SearchModel.tweets
    }

    override fun getTweetsCount(): Int {
        return SearchModel.tweets.size
    }

    override fun onQueryChanged(query: String) {
        getTweets(query)
    }

    override fun onLoadMore(query: String) {
        getTweets(query, SearchModel.max_id_str)
    }

    @SuppressLint("CheckResult")
    private fun getTweets(query: String, maxId: String = "") {
        searchView.showSwipeToRefreshProgressBar()
        RetrofitProvider.loadTweets(query, maxId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (maxId.isEmpty()) {
                        SearchModel.tweets = it.statuses
                    } else {
                        SearchModel.tweets += it.statuses
                        SearchModel.tweets.distinctBy({ it.id_str })
                    }
                    searchView.updateDate()
                    searchView.hideSwipeToRefreshProgressBar()
                }, { e ->
                    searchView.hideSwipeToRefreshProgressBar()
                    Log.e("SearchPresenter", "Exception Occurred while searching = " + e.message)
                })
    }

    override fun destroy() {
        SearchModel.destroy()
    }
}