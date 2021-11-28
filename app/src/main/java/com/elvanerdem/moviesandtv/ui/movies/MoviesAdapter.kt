package com.elvanerdem.moviesandtv.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elvanerdem.moviesandtv.databinding.LayoutItemMovieBinding
import com.elvanerdem.moviesandtv.model.movie.Movie

/** 
 * Created by elvanerdem 25.10.2021 
 */
class MoviesAdapter: PagingDataAdapter<Movie, MoviesAdapter.MoviesPagingViewHolder>(MoviesPagingDiffCallBack) {

    lateinit var movieClickListener: MovieClickListener

    override fun onBindViewHolder(holder: MoviesPagingViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesPagingViewHolder {
        return MoviesPagingViewHolder(LayoutItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class MoviesPagingViewHolder(private val binding: LayoutItemMovieBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                movieClickListener.onMovieClicked(
                    binding, getItem(absoluteAdapterPosition) as Movie
                )
            }
        }

        fun bind(item: Movie) {
            binding.apply {
                movie = item
                executePendingBindings()
            }
        }
    }

    object MoviesPagingDiffCallBack : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    interface MovieClickListener {
        fun onMovieClicked(binding: LayoutItemMovieBinding, movie: Movie)
    }


}