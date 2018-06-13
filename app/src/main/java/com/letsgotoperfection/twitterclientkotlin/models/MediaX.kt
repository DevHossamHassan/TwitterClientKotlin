package com.letsgotoperfection.twitterclientkotlin.models

data class MediaX(
        val id: Long,
        val id_str: String,
        val indices: List<Int>,
        val media_url: String,
        val media_url_https: String,
        val url: String,
        val display_url: String,
        val expanded_url: String,
        val type: String,
        val sizes: Sizes,
        val video_info: VideoInfo,
        val additional_media_info: AdditionalMediaInfo
)