package com.letsgotoperfection.twitterclientkotlin.ui.search

import RetrofitProvider
import com.letsgotoperfection.twitterclientkotlin.models.Statuse
import com.letsgotoperfection.twitterclientkotlin.ui.base.BasePresenter


/**
 * @author hossam.
 */
class SearchPresenter(private val searchView: SearchContract.View) : BasePresenter<SearchContract.View>(searchView), SearchContract.Presenter {

    override fun getExistedTracks(): List<Statuse> {
        return SearchModel.tweets
    }

    override fun getTracksListSize(): Int {
        return SearchModel.tweets.size
    }

    override fun onLoadMore(query: String) {
        loadTracks(query)
    }

    private fun loadTracks(query: String) {
//         searchView.showProgressBar()

        RetrofitProvider.loadTweets(query)
                .subscribe({
                    SearchModel.tweets = it.statuses
                    searchView.updateDate()
                    searchView.hideProgressBar()
                    searchView.hideSwipeToRefreshProgressBar()
                }, { e ->
                    searchView.hideProgressBar()
                    searchView.hideSwipeToRefreshProgressBar()
                })
    }

    override fun destroy() {
        SearchModel.destroy()
    }
}