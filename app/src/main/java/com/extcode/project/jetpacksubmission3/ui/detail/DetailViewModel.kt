package com.extcode.project.jetpacksubmission3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.extcode.project.jetpacksubmission3.data.MovieAppRepository
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import com.extcode.project.jetpacksubmission3.vo.Resource

class DetailViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    private val movieId = MutableLiveData<Int>()
    private val tvShowId = MutableLiveData<Int>()

    fun selectedMovieId(movieId: Int) {
        this.movieId.value = movieId
    }

    fun selectedTvShowId(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    var movieDetail: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) { mMovieId ->
            movieAppRepository.getMovieById(mMovieId)
        }

    var tvShowDetail: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            movieAppRepository.getTvShowById(mTvShowId)
        }

    fun setBookmarkMovie() {
        val movieResource = movieDetail.value?.data
        if (movieResource != null) {
            val newState = !movieResource.bookmarked
            movieAppRepository.setCourseBookmark(movieResource, newState)
        }
    }

    fun setBookmarkTvShow() {
        val tvShowResource = tvShowDetail.value?.data
        if (tvShowResource != null) {
            val newState = !tvShowResource.bookmarked
            movieAppRepository.setCourseBookmark(tvShowResource, newState)
        }
    }

}