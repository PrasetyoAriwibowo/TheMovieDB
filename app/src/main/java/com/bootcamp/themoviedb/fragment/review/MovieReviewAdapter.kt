package com.bootcamp.themoviedb.fragment.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.entity.movie_reviews.Review
import com.bootcamp.themoviedb.databinding.MovieReviewItemLayoutBinding
import com.bumptech.glide.Glide

class MovieReviewAdapter() : PagingDataAdapter<Review, MovieReviewItemViewHolder>(differ) {

    companion object {
        val differ = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return false
            }
        }
    }

    override fun onBindViewHolder(holder: MovieReviewItemViewHolder, position: Int) {
        holder.binding.data = getItem(position)
        getItem(position)?.authorDetails?.avatarPath?.let {
            if (it.substring(0, 5).equals("/http")) {
                Glide.with(holder.binding.root).load(it.substring(1))
                    .into(holder.binding.imgAvatar)
            } else {
                Glide.with(holder.binding.root)
                    .load("https://www.themoviedb.org/t/p/w300_and_h300_face${it}")
                    .circleCrop()
                    .into(holder.binding.imgAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewItemViewHolder {
        return MovieReviewItemViewHolder(
            MovieReviewItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }
}

class MovieReviewItemViewHolder(val binding: MovieReviewItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {}
