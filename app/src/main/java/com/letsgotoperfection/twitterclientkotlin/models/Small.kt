package com.letsgotoperfection.twitterclientkotlin.models

import java.io.Serializable

data class Small(
        val w: Int,
        val h: Int,
        val resize: String
) : Serializable