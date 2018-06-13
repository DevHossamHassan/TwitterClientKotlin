package com.letsgotoperfection.twitterclientkotlin.models

import java.io.Serializable

data class Metadata(
        val result_type: String,
        val iso_language_code: String
) : Serializable