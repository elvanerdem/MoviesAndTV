package com.elvanerdem.moviesandtv.ui.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elvanerdem.moviesandtv.databinding.LayoutItemWatchlistBinding
import com.elvanerdem.moviesandtv.model.watchlist.WatchList

/** 
 * Created by elvanerdem 26.09.2021 
 */
class WatchListAdapter(val onClickListener: OnClickListener): ListAdapter<WatchList, WatchListAdapter.WatchListViewHolder>(DiffUtilCallBack) {

    class WatchListViewHolder(private val binding: LayoutItemWatchlistBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(watchList: WatchList) {
            binding.watchList = watchList
        }

    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        val watchList = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(watchList)
        }
        holder.bind(watchList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        return WatchListViewHolder(LayoutItemWatchlistBinding.inflate(LayoutInflater.from(parent.context)))
    }

    companion object DiffUtilCallBack: DiffUtil.ItemCallback<WatchList>() {
        override fun areItemsTheSame(oldItem: WatchList, newItem: WatchList): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WatchList, newItem: WatchList): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (watchList: WatchList) -> Unit) {
        fun onClick(watchList: WatchList) = clickListener(watchList)
    }

}