package com.bootcamp.themoviedb.fragment.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.themoviedb.databinding.MoviePagingStateLayoutBinding

class DiscoverPagingStateAdapter(val getDiscoverData: () -> Unit) :
    LoadStateAdapter<DiscoverPagingStateAdapter.DiscoverMovieStateViewHolder>() {

   inner class DiscoverMovieStateViewHolder(
        private val binding: MoviePagingStateLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            when (loadState) {
                is LoadState.Error -> {
                    binding.loadingContainer.visibility = View.GONE
                }
                is LoadState.Loading -> {
                    binding.loadingContainer.visibility = View.VISIBLE
                    binding.errorContainer.visibility = View.GONE
                }
                is LoadState.NotLoading -> {
                    binding.loadingContainer.visibility = View.GONE
                    binding.errorContainer.visibility = View.GONE
                }
            }
        }
    }

    override fun onBindViewHolder(holder: DiscoverMovieStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): DiscoverMovieStateViewHolder =
        DiscoverMovieStateViewHolder(
            MoviePagingStateLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            this.bind(loadState)
        }
}