package com.extcode.project.jetpacksubmission3.di

import android.content.Context
import com.extcode.project.jetpacksubmission3.data.MovieAppRepository
import com.extcode.project.jetpacksubmission3.data.source.local.LocalDataSource
import com.extcode.project.jetpacksubmission3.data.source.local.room.MovieDatabase
import com.extcode.project.jetpacksubmission3.data.source.remote.RemoteDataSource
import com.extcode.project.jetpacksubmission3.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): MovieAppRepository {

        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieAppRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}