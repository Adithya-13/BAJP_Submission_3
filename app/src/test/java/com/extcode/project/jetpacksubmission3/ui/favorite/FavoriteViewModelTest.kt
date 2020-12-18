package com.extcode.project.jetpacksubmission3.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.extcode.project.jetpacksubmission3.data.MovieAppRepository
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieAppRepository)
    }

    @Test
    fun getBookmarkedMovies() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(movieAppRepository.getBookmarkedMovies()).thenReturn(movies)
        val movieEntities = viewModel.getBookmarkedMovies().value
        verify(movieAppRepository).getBookmarkedMovies()
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        viewModel.getBookmarkedMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getBookmarkedTvShows() {
        val dummyTvShows = pagedList
        `when`(dummyTvShows.size).thenReturn(5)
        val tvShows = MutableLiveData<PagedList<MovieEntity>>()
        tvShows.value = dummyTvShows

        `when`(movieAppRepository.getBookmarkedTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getBookmarkedTvShows().value
        verify(movieAppRepository).getBookmarkedTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(5, tvShowEntities?.size)

        viewModel.getBookmarkedTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }

}