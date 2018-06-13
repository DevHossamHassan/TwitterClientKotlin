package com.letsgotoperfection.twitterclientkotlin.models

import java.io.Serializable

data class Large(
        val w: Int,
        val h: Int,
        val resize: String
) : Serializable