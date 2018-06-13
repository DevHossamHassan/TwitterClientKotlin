package com.letsgotoperfection.twitterclientkotlin.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.letsgotoperfection.twitterclientkotlin.R
import com.letsgotoperfection.twitterclientkotlin.listeners.OnRecyclerViewScrollToTheEnd
import com.letsgotoperfection.twitterclientkotlin.models.Statuse
import com.letsgotoperfection.twitterclientkotlin.ui.NavigationManager
import com.letsgotoperfection.twitterclientkotlin.ui.base.BaseFragment
import com.letsgotoperfection.twitterclientkotlin.ui.details.DetailsFragment
import com.letsgotoperfection.twitterclientkotlin.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.search_fragment.*
import java.util.concurrent.TimeUnit


/**
 * @author hossam.
 */
class SearchFragment : BaseFragment<SearchContract.Presenter>(), SearchContract.View {
    override val fragmentLayoutResourceId = R.layout.search_fragment
    private lateinit var adapter: SearchAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun init(savedInstanceState: Bundle?) {
        presenter = SearchPresenter(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        adapter = SearchAdapter(presenter as SearchPresenter, { itemClick: Statuse -> onItemClick(itemClick) })
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20)
        recyclerView.isDrawingCacheEnabled = true
        recyclerView.itemAnimator = DefaultItemAnimator()
        layoutManager = LinearLayoutManager(activity.applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        setRecyclerViewListeners()
        setupSearchView()

    }

    private fun setRecyclerViewListeners() {
        recyclerView.addOnScrollListener(
                object : OnRecyclerViewScrollToTheEnd(layoutManager) {
                    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (layoutManager.findLastCompletelyVisibleItemPosition()
                                == adapter.itemCount - 1) {
                            presenter.onLoadMore(editTextSearch.text.toString())
                        }
                    }
                })
        setSwipeRefreshListeners()
    }

    private fun setSwipeRefreshListeners() {
        swipeRefreshLayout.setOnRefreshListener({ presenter.onQueryChanged(editTextSearch.text.toString()) })
    }

    override fun showToast(msg: String) {
        Toast.makeText(activity.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun updateDate() {
        adapter.notifyDataSetChanged()
    }

    override fun updateInsertedData(itemCount: Int) {
        adapter.notifyItemRangeInserted(presenter.getTweetsCount() - itemCount, itemCount)
    }

    override fun hideSwipeToRefreshProgressBar() {
        swipeRefreshLayout?.hideLoadingView()
    }

    override fun showSwipeToRefreshProgressBar() {
        swipeRefreshLayout?.showLoadingView()
    }

    @SuppressLint("CheckResult")
    private fun setupSearchView() {
        clear_query.setOnClickListener {
            editTextSearch.text.clear()
        }

        fromView(editTextSearch)
                .subscribeOn(Schedulers.computation())
                .debounce(400, TimeUnit.MILLISECONDS)
                .filter { it -> it.isNotEmpty() }
                .map { it.replace("[\\s]+".toRegex(), "") }
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    presenter.onQueryChanged(it)
                }
                        , {
                    Log.d("SearchFragment", "search error")
                })

        editTextSearch.onTextChanged {
            if (it.isNotEmpty()) clear_query.show() else clear_query.hide()
        }

    }

    private fun onItemClick(tweet: Statuse) {
        navigateToDetailsFragment(tweet)
    }

    override fun navigateToDetailsFragment(tweet: Statuse) {
        NavigationManager.attach(this.activity, DetailsFragment.newInstance(tweet),
                false, "DetailsFragment")
    }
}