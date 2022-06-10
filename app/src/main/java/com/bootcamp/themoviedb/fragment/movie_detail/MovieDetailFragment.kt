package com.bootcamp.themoviedb.fragment.movie_detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bootcamp.common.BaseFragment
import com.bootcamp.themoviedb.R
import com.bootcamp.themoviedb.databinding.MovieDetailsLayoutBinding
import com.bootcamp.themoviedb.fragment.review.MovieReviewAdapter
import com.bootcamp.themoviedb.view_model.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment: BaseFragment<MovieDetailViewModel, MovieDetailsLayoutBinding>() {
    override val vm: MovieDetailViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.movie_details_layout

    val movieDetailArgs: MovieDetailFragmentArgs by navArgs()
    val adapter = MovieReviewAdapter()

    override fun initBinding(binding: MovieDetailsLayoutBinding) {
        super.initBinding(binding)

        binding.recyclerReview.adapter = adapter

        vm.getMovieDetail(movieDetailArgs.discover)
        observeLiveData()
    }
}