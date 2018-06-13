package com.letsgotoperfection.twitterclientkotlin.ui.details

import android.app.Fragment
import com.letsgotoperfection.twitterclientkotlin.models.Statuse
import com.letsgotoperfection.twitterclientkotlin.ui.base.BaseContract


/**
 * @author hossam.
 */
class DetailsContract : BaseContract {

    interface View : BaseContract.View<Fragment> {
        fun shareContent()
    }

    interface Presenter : BaseContract.Presenter {
        fun getExistedTweet(): Statuse
        fun destroy()
    }
}