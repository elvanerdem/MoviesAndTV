package com.elvanerdem.moviesandtv.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.elvanerdem.moviesandtv.R
import com.elvanerdem.moviesandtv.databinding.FragmentMoviesBinding
import com.elvanerdem.moviesandtv.databinding.LayoutItemMovieBinding
import com.elvanerdem.moviesandtv.model.movie.Movie
import com.elvanerdem.moviesandtv.ui.base.BaseFragment
import com.elvanerdem.moviesandtv.ui.base.BaseLoadStateAdapter
import com.elvanerdem.moviesandtv.ui.tvseries.TvSeriesFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MoviesFragment : BaseFragment<MoviesViewModel, FragmentMoviesBinding>(), MoviesAdapter.MovieClickListener {

    private lateinit var binding: FragmentMoviesBinding
    private var movieAdapter = MoviesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val toolbar: Toolbar = binding.toolbarLayout.findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.movies)

        binding.rvMovies.apply {
            movieAdapter.movieClickListener = this@MoviesFragment
            adapter = movieAdapter.withLoadStateFooter(
                footer = BaseLoadStateAdapter(movieAdapter)
            )
        }

        lifecycleScope.launchWhenCreated {
            viewModel.movieListFlow.collectLatest { movieAdapter.submitData(it) }
        }

        movieAdapter.movieClickListener = this

        movieAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading) {
                showLoading()
            } else {
                hideLoading()
            }
        }

        return binding.root
    }

    override fun provideViewModel(): MoviesViewModel {
        return viewModels<MoviesViewModel>().value
    }

    override fun bindViewModel() {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.moviesViewModel = viewModel
    }

    override fun onMovieClicked(binding: LayoutItemMovieBinding, movie: Movie) {
        findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToMoviesDetailFragment(movie.id))
    }

}