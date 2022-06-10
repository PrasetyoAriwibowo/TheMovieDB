package com.bootcamp.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bootcamp.api_service.service.MovieReviewService
import com.bootcamp.entity.movie_reviews.Review
import java.lang.Exception

class MovieReviewDataSource(
    private val movieReviewService: MovieReviewService,
    private val movieId: Int
) : PagingSource<Int, Review>() {
    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val page = params.key ?: 1
        val prevPage = if (page == 1) null else page - 1
        try {
            val data = movieReviewService.getMovieReviews(
                movieId,
                page
            )

            if (data.isSuccessful) {
                data.body()?.let {
                    val nextPage = if (it.results.isEmpty()) null else page + 1
                    return LoadResult.Page(it.results, prevPage, nextPage)
                } ?: kotlin.run {
                    return LoadResult.Page(arrayListOf(), prevPage, null)
                }
            } else {
                return LoadResult.Error(Exception("Error Backend: ${data.code()}"))
            }

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}

object MovieReviewPager {
    fun createPager(
        pageSize: Int,
        movieReviewService: MovieReviewService,
        movieId: Int
    ): Pager<Int, Review> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            MovieReviewDataSource(movieReviewService, movieId)
        }
    )
}