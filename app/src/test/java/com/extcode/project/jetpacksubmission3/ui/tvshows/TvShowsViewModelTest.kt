package com.extcode.project.jetpacksubmission3.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.extcode.project.jetpacksubmission3.data.source.MovieAppRepository
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import com.extcode.project.jetpacksubmission3.ui.tvshows.TvShowsViewModel
import com.extcode.project.jetpacksubmission3.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest {

    private lateinit var tvShowsViewModel: TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        tvShowsViewModel = TvShowsViewModel(movieAppRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = DataDummy.generateDummyTvShows()
        val tvShows = MutableLiveData<List<MovieEntity>>()
        tvShows.value = dummyTvShows

        `when`(movieAppRepository.getAllTvShows()).thenReturn(tvShows)
        val tvShowEntities = tvShowsViewModel.getTvShows().value
        verify(movieAppRepository).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(3, tvShowEntities?.size)

        tvShowsViewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}