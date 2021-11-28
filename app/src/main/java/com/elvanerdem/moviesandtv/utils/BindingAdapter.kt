package com.elvanerdem.moviesandtv.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.elvanerdem.moviesandtv.R
import com.elvanerdem.moviesandtv.model.watchlist.WatchList
import com.elvanerdem.moviesandtv.ui.watchlist.WatchListAdapter

/**
 * Created by elvanerdem on 28.02.2021.
 */

@BindingAdapter("watchListData")
fun bindWatchListRecyclerView(recyclerView: RecyclerView, watchList: List<WatchList>?) {
    val adapter = recyclerView.adapter as WatchListAdapter
    adapter.submitList(watchList)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imagePath: String?) {
    val imgUri = Constants.IMAGE_URL.plus(imagePath)
    Glide.with(imageView.context)
        .load(imgUri)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .apply(RequestOptions()
            .placeholder(R.drawable.loading_animation))
        .into(imageView)
}