package com.letsgotoperfection.twitterclientkotlin.data

import com.letsgotoperfection.twitterclientkotlin.models.TweetX
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author hossam.
 */
interface TwitterApi {
    @GET("1.1/search/tweets.json")
    fun getTweets(@Query("q") q: String, @Query("count") count: Int): Flowable<TweetX>
}