package com.elvanerdem.moviesandtv.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.elvanerdem.moviesandtv.R
import com.elvanerdem.moviesandtv.databinding.FragmentWatchListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchListFragment : Fragment() {

    private lateinit var binding: FragmentWatchListBinding
    private val viewModel by viewModels<WatchListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watch_list, container, false)
        binding.lifecycleOwner = this
        binding.watchListViewModel = viewModel

        val toolbar: Toolbar = binding.toolbarLayout.findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.watch_list)

        binding.rvWatchList.adapter = WatchListAdapter(WatchListAdapter.OnClickListener {
            if (it.isMovie) {
                findNavController().navigate(WatchListFragmentDirections.actionWatchListFragmentToMoviesDetailFragment(it.id))
            } else {
                findNavController().navigate(WatchListFragmentDirections.actionWatchListFragmentToTVSeriesDetailFragment(it.id))
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getWatchList()
    }

}