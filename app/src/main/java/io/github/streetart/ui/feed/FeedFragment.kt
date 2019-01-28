package io.github.streetart.ui.feed


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.github.streetart.R
import io.github.streetart.network.model.Artwork


class FeedFragment : Fragment(), FeedContract.View{

    private lateinit var artsRecyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var feedPresenter: FeedPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feed, container, false)

        with(view) {
            swipeRefreshLayout = findViewById(R.id.swipe_refresh)
            artsRecyclerView = findViewById(R.id.arts_list)
        }

        feedPresenter = FeedPresenter(this)
        feedPresenter.loadArts(false)

        swipeRefreshLayout.setOnRefreshListener {
            feedPresenter.loadArts(true)
        }

        return view
    }

    override fun showArts(artworks: List<Artwork>) {
        Log.d("TAG", "showArts")
        val layoutManager = LinearLayoutManager(activity)
        artsRecyclerView.layoutManager = layoutManager

        val feedAdapter = FeedAdapter(context!!, artworks)
        feedAdapter.notifyDataSetChanged()
        artsRecyclerView.adapter = feedAdapter
    }

    override fun showArtDetails() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}