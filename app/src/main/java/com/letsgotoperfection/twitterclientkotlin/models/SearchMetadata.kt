package com.letsgotoperfection.twitterclientkotlin.models

data class SearchMetadata(
        val completed_in: Double,
        val max_id_str: String,
        val next_results: String,
        val query: String,
        val count: Int,
        val since_id: Int,
        val since_id_str: String
)