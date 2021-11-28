package com.elvanerdem.moviesandtv.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elvanerdem.moviesandtv.databinding.LayoutPagingLoadStateBinding

/** 
 * Created by elvanerdem 25.10.2021 
 */
class BaseLoadStateAdapter<T: Any, VH: RecyclerView.ViewHolder>(private val adapter: PagingDataAdapter<T, VH>): LoadStateAdapter<BaseLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BaseLoadStateAdapter.LoadStateViewHolder {
        return LoadStateViewHolder(LayoutPagingLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)) {
            adapter.retry()
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class LoadStateViewHolder(private val binding: LayoutPagingLoadStateBinding, private val retryCallBack: () -> Unit): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener {
                retryCallBack()
            }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                pbLoadState.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState is LoadState.Error
                tvLoadErrorMessage.isVisible = loadState is LoadState.Error
                tvLoadErrorMessage.text = (loadState as? LoadState.Error)?.error?.message
            }
        }
    }
}