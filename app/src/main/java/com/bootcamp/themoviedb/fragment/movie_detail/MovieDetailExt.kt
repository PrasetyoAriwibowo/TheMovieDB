package com.bootcamp.themoviedb.fragment.movie_detail

import android.util.Log
import android.view.View
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import com.bootcamp.themoviedb.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun MovieDetailFragment.observeLiveData() = with(vm) {

    observeResponseData(movieDetailData,
        success = {

            binding.data = it.first
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${it.first?.posterPath}")
                .transform(CenterCrop(),RoundedCorners(25))
                .into(binding.imgPoster)
            if (it.second?.results?.isNotEmpty() == true) {
                showVideo(it.second.results[0].key)
            }

            binding.progressBar.visibility = View.GONE
            binding.retryButton.visibility = View.GONE
        },
        loading = {
            binding.progressBar.visibility = View.VISIBLE
        },
        error = {
            binding.progressBar.visibility = View.GONE
            binding.retryButton.visibility = View.VISIBLE
            binding.retryButton.setOnClickListener {
                vm.getMovieDetail(movieDetailArgs.discover)
            }
        })

    vm.movieReviewData.observe(this@observeLiveData) {
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }
}

private fun MovieDetailFragment.showVideo(it: String) {
    val youtubeFragment = YouTubePlayerSupportFragmentX.newInstance()
    with(parentFragmentManager) {
        beginTransaction().apply {
            add(R.id.fragmentVideoTrailer, youtubeFragment)
            commit()
        }
    }
    youtubeFragment.initialize("AIzaSyA3QoRqOlz-H1JjQftChkTbWiEataY6KRg",
        object : YouTubePlayerSupportFragmentX.OnInitializedListener() {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.cueVideo(it)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Log.e("YoutubePlayer", "error")
            }
        }
    )
}
