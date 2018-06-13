package com.letsgotoperfection.twitterclientkotlin.models

import java.io.Serializable

data class AdditionalMediaInfo(
        val title: String,
        val description: String,
        val embeddable: Boolean,
        val monetizable: Boolean
) : Serializable