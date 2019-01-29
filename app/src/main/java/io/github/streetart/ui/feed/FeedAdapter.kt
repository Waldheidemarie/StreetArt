package io.github.streetart.ui.feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.streetart.R
import io.github.streetart.loadImage
import io.github.streetart.network.model.Artwork

class FeedAdapter (
    private var data: List<Artwork>?,
    private val presenter: FeedPresenter
)
    : RecyclerView.Adapter<FeedViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FeedViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.art_item, parent, false)
        return FeedViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        if (!data!![position].artists.isNullOrEmpty()) {
            holder.author.text = data!![position].artists[0].name
        }

        if (!data!![position].name.isNullOrBlank()) {
            holder.name.text = data!![position].name
        }

        if (!data!![position].photos.isNullOrEmpty()) {
            holder.image.loadImage(data!![position].photos[0].image)
        }

        holder.item.setOnClickListener {
            presenter.openArtDetails(data!![position])
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}