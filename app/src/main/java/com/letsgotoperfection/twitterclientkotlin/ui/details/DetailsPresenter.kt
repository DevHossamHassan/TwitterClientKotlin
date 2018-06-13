package com.letsgotoperfection.twitterclientkotlin.ui.details

import com.letsgotoperfection.twitterclientkotlin.models.Statuse
import com.letsgotoperfection.twitterclientkotlin.ui.base.BasePresenter


/**
 * @author hossam.
 */
class DetailsPresenter(private val detailsView: DetailsContract.View)
    : BasePresenter<DetailsContract.View>(detailsView), DetailsContract.Presenter {

    override fun getExistedTweet(): Statuse {
        return DetailsModel.tweet
    }

    override fun destroy() {
        DetailsModel.destroy()
    }
}