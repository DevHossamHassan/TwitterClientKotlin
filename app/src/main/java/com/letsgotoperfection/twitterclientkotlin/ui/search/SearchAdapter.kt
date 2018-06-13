package com.letsgotoperfection.twitterclientkotlin.ui.search

import android.support.v7.widget.RecyclerView
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.letsgotoperfection.twitterclientkotlin.R
import com.letsgotoperfection.twitterclientkotlin.listeners.OnRecyclerViewClickListener
import com.letsgotoperfection.twitterclientkotlin.models.Statuse
import com.letsgotoperfection.twitterclientkotlin.utils.loadUrl
import kotlinx.android.synthetic.main.tweet_list_item_text.view.*
import kotlinx.android.synthetic.main.twitter_action_bar.view.*


/**
 * @author hossam.
 */
class SearchAdapter(private val presenter: SearchPresenter) : RecyclerView.Adapter<SearchAdapter.HotTracksListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotTracksListHolder {

        return HotTracksListHolder((
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.tweet_list_item_text, parent, false)),
                object : OnRecyclerViewClickListener {
                    override fun onRecyclerViewItemClicked(v: View, position: Int) {
                        //todo  implement on
                    }
                })
    }

    override fun onBindViewHolder(holder: HotTracksListHolder, position: Int) {
        holder.bind(presenter.getExistedTweets()[position])
    }

    override fun getItemCount(): Int {
        return presenter.getTweetsCount()
    }

    class HotTracksListHolder(itemView: View, private var onRecyclerViewClickListener: OnRecyclerViewClickListener)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind(tweet: Statuse) {
            itemView.twitter_client_kotlin_author_full_name.text = tweet.user.name
            itemView.twitter_client_kotlin_author_twitter_handel.text =
                    itemView.context.resources.getString(R.string.twitterHandel, tweet.user.screen_name)
            if (!tweet.user.verified) {
                itemView.twitter_client_kotlin_author_full_name.setCompoundDrawables(
                        null, null, null, null)
            }
            itemView.twitter_client_kotlin_tweet_text.text = tweet.text
            itemView.twitter_client_kotlin_tweet_text.movementMethod = LinkMovementMethod.getInstance()
            itemView.txtReplyCount.text = tweet.retweet_count.toString()
            itemView.txtRetweetCount.text = tweet.retweet_count.toString()
            itemView.txtFavoriteCount.text = tweet.favorite_count.toString()

            try {
                itemView.imgTweet.loadUrl(tweet.entities.media[0].media_url)
            } catch (e: NullPointerException) {
                itemView.imgTweet.visibility = View.GONE
            }
            itemView.twitter_client_kotlin_author_avatar.loadUrl(tweet.user.profile_image_url)
        }

        override fun onClick(view: View) {
            onRecyclerViewClickListener.onRecyclerViewItemClicked(view, this.adapterPosition)
        }
    }
}
