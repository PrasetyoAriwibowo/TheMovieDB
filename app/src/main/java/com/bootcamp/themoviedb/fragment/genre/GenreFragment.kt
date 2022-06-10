package com.bootcamp.themoviedb.fragment.genre

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bootcamp.common.BaseFragment
import com.bootcamp.entity.genre.ListGenre
import com.bootcamp.themoviedb.R
import com.bootcamp.themoviedb.databinding.GenreListLayoutBinding
import com.bootcamp.themoviedb.view_model.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreFragment : BaseFragment<GenreViewModel, GenreListLayoutBinding>() {
    override val vm: GenreViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.genre_list_layout

    override fun initBinding(binding: GenreListLayoutBinding) {
        super.initBinding(binding)

        binding.recyclerGenre.adapter = adapter
        observeLiveData()

        binding.buttonGenre.setOnClickListener {
            findNavController().navigate(
                GenreFragmentDirections.actionGenreFragmentToDiscoverFragment(
                    vm.selection.value.orEmpty().map { it.id }.joinToString(
                        separator = ","
                    )
                )
            )
        }
    }

    val adapter = GenreAdapter(::startActionMode, {
        vm.selection.value.orEmpty()
    }) {
        toggleClick(it)
    }

    override fun onDestroy() {
        super.onDestroy()
        actionMode = null
    }
}