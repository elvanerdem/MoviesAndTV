package com.elvanerdem.moviesandtv.ui.search

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.elvanerdem.moviesandtv.R
import com.elvanerdem.moviesandtv.databinding.FragmentSearchBinding
import com.elvanerdem.moviesandtv.databinding.LayoutItemSearchBinding
import com.elvanerdem.moviesandtv.model.search.SearchResult
import com.elvanerdem.moviesandtv.ui.base.BaseFragment
import com.elvanerdem.moviesandtv.ui.base.BaseLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(), SearchAdapter.SearchOnClickListener {

    private lateinit var binding: FragmentSearchBinding
    private var searchAdapter = SearchAdapter()

    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val toolbar: Toolbar = binding.toolbar
        toolbar.title = getString(R.string.search)

        binding.rvSearchResult.apply {
            searchAdapter.searchOnClickListener = this@SearchFragment
            adapter = searchAdapter.withLoadStateFooter(
                footer = BaseLoadStateAdapter(searchAdapter)
            )
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    search(query)
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    search(newText)
                }
                return false
            }
        })

        return binding.root
    }

    override fun provideViewModel(): SearchViewModel {
        return viewModels<SearchViewModel>().value
    }

    override fun bindViewModel() {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        binding.searchViewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun search(query: String) {
        lifecycleScope.launchWhenCreated {
            viewModel.search(query).collectLatest {
                searchAdapter.submitData(it)
            }
        }
    }

    override fun searchResultClicked(binding: LayoutItemSearchBinding, item: SearchResult) {
        when (item.mediaType) {
            "movie" -> {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToMoviesDetailFragment(item.id))
            }
            "tv"-> {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToTVSeriesDetailFragment(item.id))
            }
        }
    }


}