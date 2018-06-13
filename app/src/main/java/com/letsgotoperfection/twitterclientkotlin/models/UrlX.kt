package com.letsgotoperfection.twitterclientkotlin.models

import java.io.Serializable

data class UrlX(
        val url: String,
        val expanded_url: String,
        val display_url: String,
        val indices: List<Int>
) : Serializable