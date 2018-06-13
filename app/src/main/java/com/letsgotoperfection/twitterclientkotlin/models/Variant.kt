package com.letsgotoperfection.twitterclientkotlin.models

import java.io.Serializable

data class Variant(
        val content_type: String,
        val url: String,
        val bitrate: Int
) : Serializable