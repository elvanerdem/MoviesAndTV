package com.elvanerdem.moviesandtv.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elvanerdem.moviesandtv.databinding.LayoutItemSearchBinding
import com.elvanerdem.moviesandtv.model.search.SearchResult

/**
 * Created by elvanerdem on 25.10.2021 
 */
class SearchAdapter: PagingDataAdapter<SearchResult, SearchAdapter.SearchResultViewHolder>(SearchResultDiffCallBack) {

    lateinit var searchOnClickListener: SearchOnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.SearchResultViewHolder {
        return SearchResultViewHolder(LayoutItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchResultViewHolder, position: Int) {
        holder.bind(getItem(position) as SearchResult)
    }

    inner class SearchResultViewHolder(private val binding: LayoutItemSearchBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                searchOnClickListener.searchResultClicked(binding, getItem(absoluteAdapterPosition) as SearchResult)
            }
        }

        fun bind(item: SearchResult) {
            binding.apply {
                searchResult = item
                executePendingBindings()
            }
        }
    }

    object SearchResultDiffCallBack : DiffUtil.ItemCallback<SearchResult>() {

        override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
            return oldItem == newItem
        }

    }

    interface SearchOnClickListener {
        fun searchResultClicked(binding: LayoutItemSearchBinding, item: SearchResult)
    }

}