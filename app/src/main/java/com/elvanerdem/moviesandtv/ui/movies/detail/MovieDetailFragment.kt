package com.elvanerdem.moviesandtv.ui.movies.detail

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.elvanerdem.moviesandtv.R
import com.elvanerdem.moviesandtv.databinding.FragmentMoviesDetailBinding
import com.elvanerdem.moviesandtv.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies_detail.*
import kotlinx.android.synthetic.main.fragment_movies_detail.view.*

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel, FragmentMoviesDetailBinding>() {

    private lateinit var binding: FragmentMoviesDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val args = MovieDetailFragmentArgs.fromBundle(requireArguments())

        viewModel.getMovieDetail(args.movieId)
        viewModel.checkMovieInWatchList(args.movieId)

        subscribeMovieDetail()
        subscribeIsItemInWatchList()

        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }

    override fun provideViewModel(): MovieDetailViewModel {
        return viewModels<MovieDetailViewModel>().value
    }

    override fun bindViewModel() {
        binding = FragmentMoviesDetailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.movieDetailViewModel = viewModel
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

    private fun subscribeMovieDetail() {
        viewModel.movieDetailResponse.observe(viewLifecycleOwner, {
            viewModel.setMovieDetail(it)
        })
    }

}