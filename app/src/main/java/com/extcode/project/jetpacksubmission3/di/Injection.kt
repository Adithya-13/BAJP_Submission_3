package com.extcode.project.jetpacksubmission3.di

import com.extcode.project.jetpacksubmission3.data.source.MovieAppRepository
import com.extcode.project.jetpacksubmission3.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(): MovieAppRepository {

        val remoteRepository = RemoteDataSource.getInstance()

        return MovieAppRepository.getInstance(remoteRepository)
    }
}