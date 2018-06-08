package com.letsgotoperfection.twitterclientkotlin.ui

import com.letsgotoperfection.twitterclientkotlin.R
import com.letsgotoperfection.twitterclientkotlin.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int {
        return R.layout.main_activity
    }

    override fun getTitleResourceId(): String {
        return getString(R.string.app_name)
    }

    override fun init() {
        //TODO navigate to the host fragment here
    }
}
