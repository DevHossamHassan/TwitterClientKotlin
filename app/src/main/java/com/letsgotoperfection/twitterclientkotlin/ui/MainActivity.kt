package com.letsgotoperfection.twitterclientkotlin.ui

import com.letsgotoperfection.twitterclientkotlin.R
import com.letsgotoperfection.twitterclientkotlin.ui.base.BaseActivity
import com.letsgotoperfection.twitterclientkotlin.ui.search.SearchFragment

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int {
        return R.layout.main_activity
    }

    override fun getTitleResourceId(): String {
        return getString(R.string.app_name)
    }

    override fun init() {
        NavigationManager.attachAsRoot(this, SearchFragment())
    }
}
