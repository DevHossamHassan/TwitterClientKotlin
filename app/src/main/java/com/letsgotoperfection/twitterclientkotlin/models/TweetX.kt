package com.letsgotoperfection.twitterclientkotlin.models

import java.io.Serializable

data class TweetX(
        val statuses: List<Statuse>,
        val search_metadata: SearchMetadata
) : Serializable