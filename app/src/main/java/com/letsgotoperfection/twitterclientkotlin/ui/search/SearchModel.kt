package com.letsgotoperfection.twitterclientkotlin.ui.search

import com.letsgotoperfection.twitterclientkotlin.models.Statuse


/**
 * @author hossam.
 */
object SearchModel {
    var tweets: List<Statuse> = listOf()
    var max_id_str: String? = ""
        get() = (SearchModel.tweets.minBy { it.id })?.id_str

    fun destroy() {
    }
}