package com.letsgotoperfection.twitterclientkotlin.models

import java.io.Serializable

data class Sizes(
        val large: Large,
        val medium: Medium,
        val thumb: Thumb,
        val small: Small
) : Serializable