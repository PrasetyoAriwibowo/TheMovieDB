package com.bootcamp.themoviedb.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.bootcamp.api_service.usecase.DiscoverPagingUseCase
import com.bootcamp.common.BaseViewModel
import com.bootcamp.entity.discover.DiscoverMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    application: Application,
    val discoverPagingUseCase: DiscoverPagingUseCase
) : BaseViewModel(application) {

    val discoverDataPaging = MutableLiveData<PagingData<DiscoverMovie>>()

    fun getDiscover(genreId: String){
        viewModelScope.launch {
            discoverPagingUseCase.invoke(genreId).collect {
                discoverDataPaging.postValue(it)
            }
        }
    }
}