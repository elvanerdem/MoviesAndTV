package com.elvanerdem.moviesandtv.ui.tvseries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elvanerdem.moviesandtv.databinding.LayoutItemTvseriesBinding
import com.elvanerdem.moviesandtv.model.tvseries.TVSeries

/** 
 * Created by elvanerdem 25.10.2021 
 */
class TVSeriesAdapter: PagingDataAdapter<TVSeries, TVSeriesAdapter.TVSeriesViewHolder>(TVSeriesDiffCallBack) {

    lateinit var tvSeriesClickListener: TvSeriesClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVSeriesAdapter.TVSeriesViewHolder {
        return TVSeriesViewHolder(LayoutItemTvseriesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TVSeriesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

    }

    inner class TVSeriesViewHolder(private val binding: LayoutItemTvseriesBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                tvSeriesClickListener.onTVSeriesClicked(binding, getItem(absoluteAdapterPosition) as TVSeries)
            }
        }

        fun bind(item: TVSeries) {
            binding.apply {
                tvseries = item
                executePendingBindings()
            }
        }
    }

    object TVSeriesDiffCallBack : DiffUtil.ItemCallback<TVSeries>() {
        override fun areItemsTheSame(oldItem: TVSeries, newItem: TVSeries): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TVSeries, newItem: TVSeries): Boolean {
            return oldItem == newItem
        }
    }

    interface TvSeriesClickListener {
        fun onTVSeriesClicked(binding: LayoutItemTvseriesBinding, tvSeries: TVSeries)
    }
}