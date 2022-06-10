package com.bootcamp.themoviedb.fragment.discover

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun DiscoverFragment.observeLiveData() = with(vm){
    vm.getDiscover(discoverArgs.genre)

    vm.discoverDataPaging.observe(this@observeLiveData) {
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }
}

