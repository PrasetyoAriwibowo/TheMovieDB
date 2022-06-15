package com.bootcamp.themoviedb.fragment.discover

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.bootcamp.common.BaseFragment
import com.bootcamp.themoviedb.R
import com.bootcamp.themoviedb.databinding.DiscoverListLayoutBinding
import com.bootcamp.themoviedb.view_model.DiscoverViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DiscoverFragment : BaseFragment<DiscoverViewModel, DiscoverListLayoutBinding>() {
    override val vm: DiscoverViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.discover_list_layout
    val discoverArgs: DiscoverFragmentArgs by navArgs()
    val adapter = DiscoverAdapter() {
        findNavController().navigate(
            DiscoverFragmentDirections.discoverFragmentToMovieDetailFragment(
                it
            )
        )
    }
    val loadStateAdapterDiscover = DiscoverPagingStateAdapter(getDiscoverData = {
        vm.getDiscover(discoverArgs.genre)
    })

    override fun initBinding(binding: DiscoverListLayoutBinding) {
        super.initBinding(binding)
        binding.recyclerDiscover.adapter = adapter

        observeLiveData()

        adapter.addLoadStateListener {
            if (it.refresh is LoadState.Error || it.append is LoadState.Error ||
                it.prepend is LoadState.Error
            ) {
                binding.retryButton.visibility = View.VISIBLE
                binding.recyclerDiscover.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            } else if (it.refresh is LoadState.Loading /*&& adapter.itemCount == 0*/) {
                binding.retryButton.visibility = View.GONE
                binding.recyclerDiscover.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE

            } else if (it.refresh is LoadState.NotLoading) {
                binding.retryButton.visibility = View.GONE
                binding.recyclerDiscover.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }

        binding.retryButton.setOnClickListener {
            adapter.retry()
        }
        binding.recyclerDiscover.adapter = adapter.withLoadStateFooter(loadStateAdapterDiscover)
    }
}