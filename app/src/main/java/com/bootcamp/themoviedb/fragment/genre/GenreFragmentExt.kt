package com.bootcamp.themoviedb.fragment.genre

import android.view.View
import androidx.appcompat.view.ActionMode
import androidx.navigation.fragment.findNavController
import com.bootcamp.entity.genre.Genre
import com.bootcamp.ext.toggle

var actionMode: ActionMode? = null

fun GenreFragment.observeLiveData() = with(vm) {

    observeResponseData(dataGenre,
        success = {
            binding.progressBar.visibility = View.GONE
            adapter.submitData(it)
            binding.retryButton.visibility = View.GONE
        },
        loading = {
            binding.progressBar.visibility = View.VISIBLE
        },
        error = {
            binding.progressBar.visibility = View.GONE
            binding.retryButton.visibility = View.VISIBLE
            binding.retryButton.setOnClickListener {
                vm.getGenre()
            }
        })

    selection.observe(this@observeLiveData) {
        if (it.isEmpty()) {
            binding.buttonGenre.visibility = View.GONE
        } else {
            binding.buttonGenre.visibility = View.VISIBLE
        }
    }
}

fun GenreFragment.toggleClick(genre: Genre) {
    if (vm.selection?.value?.isNotEmpty() == true) {
        adapter.toggleSelection(genre) {
            vm.selection.toggle(genre)
        }
    } else {
        findNavController().navigate(
            GenreFragmentDirections.actionGenreFragmentToDiscoverFragment(
                genre.id.toString()
            )
        )
    }
}

fun GenreFragment.startActionMode(genre: Genre) {
    adapter.toggleSelection(genre) {
        vm.selection.toggle(genre)
    }
}
