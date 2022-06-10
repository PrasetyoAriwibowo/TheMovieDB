package com.bootcamp.themoviedb.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bootcamp.api_service.usecase.GenreUsecase
import com.bootcamp.common.AppResponse
import com.bootcamp.common.BaseViewModel
import com.bootcamp.entity.genre.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    application: Application,
    val genreUsecase: GenreUsecase
) : BaseViewModel(application) {

    val dataGenre = MutableLiveData<AppResponse<List<Genre>>>()
    val selection = MutableLiveData<List<Genre>>(arrayListOf())

    init {
        getGenre()
    }

    fun getGenre(){
        viewModelScope.launch {
            genreUsecase().collect {
                dataGenre.postValue(it)
            }
        }
    }
}
