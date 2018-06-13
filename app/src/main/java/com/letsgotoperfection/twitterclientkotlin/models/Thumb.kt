package com.letsgotoperfection.twitterclientkotlin.models

import java.io.Serializable

data class Thumb(
        val w: Int,
        val h: Int,
        val resize: String
) : Serializable