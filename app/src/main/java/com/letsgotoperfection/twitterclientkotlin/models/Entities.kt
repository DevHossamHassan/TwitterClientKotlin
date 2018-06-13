package com.letsgotoperfection.twitterclientkotlin.models

import java.io.Serializable

data class Entities(
        val hashtags: List<Any>,
        val symbols: List<Any>,
        val user_mentions: List<Any>,
        val urls: List<Any>,
        val media: List<Media>
) : Serializable