
import com.letsgotoperfection.twitterclientkotlin.BuildConfig
import com.letsgotoperfection.twitterclientkotlin.data.Oauth1SigningInterceptor
import com.letsgotoperfection.twitterclientkotlin.data.TwitterApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * @author hossam.
 */
object RetrofitProvider {
    private const val BASE_URL = "https://api.twitter.com/"
    private lateinit var twitterApi: TwitterApi

    init {
        val retrofit = initRetrofit()
        initServices(retrofit)
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build()
    }


    private fun initServices(retrofit: Retrofit) {
        twitterApi = retrofit.create(TwitterApi::class.java)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val oauth1SigningInterceptor: Oauth1SigningInterceptor = Oauth1SigningInterceptor.Builder()
                .consumerKey(BuildConfig.TWITTER_CONSUMER_KEY)
                .consumerSecret(BuildConfig.TWITTER_CONSUMER_SECRET)
                .accessToken(BuildConfig.TWITTER_ACCESS_TOKEN)
                .accessSecret(BuildConfig.TWITTER_ACCESS_TOKEN_SECRET)
                .build()

        return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(oauth1SigningInterceptor)
                .build()
    }

    fun loadTweets(query: String, maxId: String) =
            if (maxId.isEmpty()) {
            twitterApi.getTweets(query, 20)
            } else {
                twitterApi.getTweets(query, 20, maxId)
            }

}