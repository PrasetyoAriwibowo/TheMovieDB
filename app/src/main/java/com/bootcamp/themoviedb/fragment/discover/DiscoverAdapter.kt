package com.bootcamp.themoviedb.fragment.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.entity.discover.DiscoverMovie
import com.bootcamp.themoviedb.databinding.DiscoverItemLayoutBinding
import com.bumptech.glide.Glide

class DiscoverAdapter(
    val navigateToMovieDetail: (Int) -> Unit
) : PagingDataAdapter<DiscoverMovie, DiscoverItemViewHolder>(differ) {

    override fun onBindViewHolder(holder: DiscoverItemViewHolder, position: Int) {
        holder.binding.data = getItem(position)
        holder.binding.cardView.animation = AnimationUtils.loadAnimation(holder.itemView.context, com.bootcamp.themoviedb.R.anim.animation)
        holder.binding.root.setOnClickListener {
            getItem(position)?.let { it ->
                navigateToMovieDetail.invoke(it.id)
            }
        }
        Glide.with(holder.binding.root)
            .load("https://image.tmdb.org/t/p/w500${getItem(position)?.posterPath}")
            .into(holder.binding.imagePoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverItemViewHolder {
        return DiscoverItemViewHolder(
            DiscoverItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<DiscoverMovie>() {

            override fun areItemsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DiscoverMovie,
                newItem: DiscoverMovie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class DiscoverItemViewHolder(
    val binding: DiscoverItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root)
