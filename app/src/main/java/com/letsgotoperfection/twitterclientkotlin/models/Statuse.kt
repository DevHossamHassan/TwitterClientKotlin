package com.letsgotoperfection.twitterclientkotlin.models

import java.io.Serializable

data class Statuse(
        val created_at: String,
        val id: Long,
        val id_str: String,
        val text: String,
        val truncated: Boolean,
        val entities: Entities,
        val metadata: Metadata,
        val source: String,
        val in_reply_to_status_id: Any,
        val in_reply_to_status_id_str: Any,
        val in_reply_to_user_id: Any,
        val in_reply_to_user_id_str: Any,
        val in_reply_to_screen_name: Any,
        val user: User,
        val geo: Any,
        val coordinates: Any,
        val place: Any,
        val contributors: Any,
        val is_quote_status: Boolean,
        val retweet_count: Int,
        val favorite_count: Int,
        val favorited: Boolean,
        val retweeted: Boolean,
        val lang: String,
        val extended_entities: ExtendedEntities,
        val possibly_sensitive: Boolean
) : Serializable