package com.bootcamp.themoviedb.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.bootcamp.api_service.usecase.MovieDetailVideoUsecase
import com.bootcamp.api_service.usecase.MovieReviewPagingUsecase
import com.bootcamp.common.AppResponse
import com.bootcamp.common.BaseViewModel
import com.bootcamp.entity.movie_detail.MovieDetailsResponse
import com.bootcamp.entity.movie_reviews.Review
import com.bootcamp.entity.movie_video.MovieVideoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    application: Application,
    val movieDetailVideoUsecase: MovieDetailVideoUsecase,
    val movieReviewPagingUsecase: MovieReviewPagingUsecase
) : BaseViewModel(application) {

    val movieDetailData =
        MutableLiveData<AppResponse<Pair<MovieDetailsResponse, MovieVideoResponse>>>()
    val movieReviewData = MutableLiveData<PagingData<Review>>()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            movieDetailVideoUsecase(movieId).collect {
                movieDetailData.postValue(it)
            }

            movieReviewPagingUsecase.invoke(movieId).collect {
                movieReviewData.postValue(it)
            }
        }
    }
}