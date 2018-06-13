package com.letsgotoperfection.twitterclientkotlin.models

import java.io.Serializable

data class VideoInfo(
        val aspect_ratio: List<Int>,
        val duration_millis: Int,
        val variants: List<Variant>
) : Serializable