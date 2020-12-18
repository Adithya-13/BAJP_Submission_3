package com.extcode.project.jetpacksubmission3.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.extcode.project.jetpacksubmission3.data.MovieAppRepository
import com.extcode.project.jetpacksubmission3.di.Injection
import com.extcode.project.jetpacksubmission3.ui.detail.DetailViewModel
import com.extcode.project.jetpacksubmission3.ui.favorite.FavoriteViewModel
import com.extcode.project.jetpacksubmission3.ui.movies.MoviesViewModel
import com.extcode.project.jetpacksubmission3.ui.tvshows.TvShowsViewModel


class ViewModelFactory private constructor(private val mMovieAppRepository: MovieAppRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(mMovieAppRepository) as T
            }
            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                TvShowsViewModel(mMovieAppRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mMovieAppRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mMovieAppRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}