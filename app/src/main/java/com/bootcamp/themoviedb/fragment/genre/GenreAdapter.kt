package com.bootcamp.themoviedb.fragment.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.animated.R
import com.bootcamp.entity.genre.Genre
import com.bootcamp.themoviedb.databinding.GenreItemLayoutBinding


class GenreAdapter(
    val startSupportActionModeClick: (Genre) -> Unit,
    val getSelection: () -> List<Genre>,
    val toggleClick: (Genre) -> Unit
) : RecyclerView.Adapter<GenreViewHolder>() {

    private val differ = AsyncListDiffer<Genre>(this, itemCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            GenreItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.data = data
        holder.binding.cardView.animation = AnimationUtils.loadAnimation(holder.itemView.context, com.bootcamp.themoviedb.R.anim.animation)
        holder.binding.isSelected = getSelection().contains(data)
        holder.binding.root.setOnLongClickListener {
            startSupportActionModeClick(data)
            true
        }
        holder.binding.root.setOnClickListener {
            toggleClick(data)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitData(data: List<Genre>) {
        differ.submitList(data)
    }

    fun toggleSelection(genre: Genre, changes: () -> Unit) {
        val toggleDiffUtil = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = differ.currentList.size

            override fun getNewListSize(): Int = differ.currentList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return differ.currentList[oldItemPosition] != genre
            }
        }
        val differ = DiffUtil.calculateDiff(toggleDiffUtil)
        changes()
        differ.dispatchUpdatesTo(this)
    }

    companion object {
        val itemCallBack = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return true
            }
        }
    }
}

class GenreViewHolder(val binding: GenreItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root)