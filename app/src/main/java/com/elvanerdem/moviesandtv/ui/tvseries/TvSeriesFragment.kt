package com.elvanerdem.moviesandtv.ui.tvseries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.elvanerdem.moviesandtv.R
import com.elvanerdem.moviesandtv.databinding.FragmentTvSeriesBinding
import com.elvanerdem.moviesandtv.databinding.LayoutItemTvseriesBinding
import com.elvanerdem.moviesandtv.model.tvseries.TVSeries
import com.elvanerdem.moviesandtv.ui.base.BaseFragment
import com.elvanerdem.moviesandtv.ui.base.BaseLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TvSeriesFragment : BaseFragment<TvSeriesViewModel, FragmentTvSeriesBinding>(), TVSeriesAdapter.TvSeriesClickListener {

    private lateinit var binding: FragmentTvSeriesBinding
    private val tvSeriesAdapter = TVSeriesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_series, container,false)
        binding.lifecycleOwner = this
        binding.tvSeriesListViewModel = viewModel

        val toolbar: Toolbar = binding.toolbarLayout.findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.tv_series)

        binding.rvTvSeries.apply {
            tvSeriesAdapter.tvSeriesClickListener = this@TvSeriesFragment
            adapter = tvSeriesAdapter.withLoadStateFooter(
                footer = BaseLoadStateAdapter(tvSeriesAdapter)
            )
        }

        lifecycleScope.launchWhenCreated {
            viewModel.tvSeriesListFlow.collectLatest {
                tvSeriesAdapter.submitData(it)
            }
        }

        tvSeriesAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading) {
                showLoading()
            } else {
                hideLoading()
            }
        }

        return binding.root
    }

    override fun provideViewModel(): TvSeriesViewModel {
        return viewModels<TvSeriesViewModel>().value
    }

    override fun bindViewModel() {
        binding = FragmentTvSeriesBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.tvSeriesListViewModel = viewModel
    }

    override fun onTVSeriesClicked(binding: LayoutItemTvseriesBinding, tvSeries: TVSeries) {
        findNavController().navigate(TvSeriesFragmentDirections.actionTvSeriesFragmentToTVSeriesDetailFragment(tvSeries.id))
    }

}