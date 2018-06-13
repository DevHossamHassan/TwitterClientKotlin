package com.letsgotoperfection.twitterclientkotlin.ui.details

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import com.letsgotoperfection.twitterclientkotlin.R
import com.letsgotoperfection.twitterclientkotlin.models.Statuse
import com.letsgotoperfection.twitterclientkotlin.ui.NavigationManager
import com.letsgotoperfection.twitterclientkotlin.ui.base.BaseFragment
import com.letsgotoperfection.twitterclientkotlin.utils.loadUrl
import com.letsgotoperfection.twitterclientkotlin.utils.putArgs
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.twitter_action_bar.*

/**
 * @author hossam.
 */
class DetailsFragment : BaseFragment<DetailsContract.Presenter>(), DetailsContract.View {

    override val fragmentLayoutResourceId = R.layout.fragment_details
    lateinit var tweet: Statuse

    companion object {
        fun newInstance(tweet: Statuse) = DetailsFragment().putArgs {
            val args = Bundle()
            args.putSerializable("tweet", tweet)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun init(savedInstanceState: Bundle?) {
        presenter = DetailsPresenter(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tweet = arguments.getSerializable("tweet") as Statuse
        setUpViews()
    }

    fun setUpViews() {
        twitter_client_kotlin_author_full_name.text = tweet.user.name
        twitter_client_kotlin_author_twitter_handel.text =
                resources.getString(R.string.twitterHandel, tweet.user.screen_name)
        if (!tweet.user.verified) {
            twitter_client_kotlin_author_full_name.setCompoundDrawables(
                    null, null, null, null)
        }
        twitter_client_kotlin_tweet_text.text = tweet.text
        twitter_client_kotlin_tweet_text.movementMethod = LinkMovementMethod.getInstance()
        txtReplyCount.text = tweet.retweet_count.toString()
        txtRetweetCount.text = tweet.retweet_count.toString()
        txtFavoriteCount.text = tweet.favorite_count.toString()
        txtTitle.text = getString(R.string.detailsTitle)
        try {
            imgTweet.loadUrl(tweet.entities.media[0].media_url)
        } catch (e: NullPointerException) {
            imgTweet.visibility = View.GONE
        }
        twitter_client_kotlin_author_avatar.loadUrl(tweet.user.profile_image_url)
        btnShare.setOnClickListener {
            shareContent()
        }
        btnBack.setOnClickListener {
            NavigationManager.navigateBack(this.activity)
        }
    }

    override fun shareContent() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, tweet.text)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.send_to)))
    }
}