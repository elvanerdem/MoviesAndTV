package com.elvanerdem.moviesandtv.ui.tvseries.detail

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.elvanerdem.moviesandtv.R
import com.elvanerdem.moviesandtv.databinding.FragmentTvSeriesBinding
import com.elvanerdem.moviesandtv.databinding.FragmentTvSeriesDetailBinding
import com.elvanerdem.moviesandtv.ui.base.BaseFragment
import com.elvanerdem.moviesandtv.ui.tvseries.TvSeriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies_detail.*
import kotlinx.android.synthetic.main.fragment_movies_detail.view.*

/**
 * Created by elvanerdem on 28.02.2021.
 */
@AndroidEntryPoint
class TVSeriesDetailFragment : BaseFragment<TVSeriesDetailViewModel, FragmentTvSeriesBinding>() {

    private lateinit var binding: FragmentTvSeriesDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tvId = TVSeriesDetailFragmentArgs.fromBundle(requireArguments()).tvId

        subscribeHasError()

        viewModel.getTVSeriesDetail(tvId)
        viewModel.checkTVSeriesInWatchList(tvId)

        subscribeIsItemInWatchList()
        subscribeTVSeriesDetail()

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    private fun subscribeTVSeriesDetail() {
        viewModel.tvSeriesDetailResponse.observe(viewLifecycleOwner, {
            viewModel.setTVSeriesDetail(it)
        })
    }

    private fun subscribeHasError() {
        viewModel.hasError.observe(viewLifecycleOwner, {
            binding.llContent.isVisible = !it
        })
    }

    private fun subscribeIsItemInWatchList() {
        viewModel.isItemInWatchList.observe(viewLifecycleOwner, {
            if (it) {
                ibAddWatchList.ibAddWatchList.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_filled)
            } else {
                ibAddWatchList.ibAddWatchList.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite)
            }
        })
    }

    override fun provideViewModel(): TVSeriesDetailViewModel {
        return viewModels<TVSeriesDetailViewModel>().value
    }

    override fun bindViewModel() {
        binding = FragmentTvSeriesDetailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.tvSeriesDetailViewModel = viewModel
    }

}