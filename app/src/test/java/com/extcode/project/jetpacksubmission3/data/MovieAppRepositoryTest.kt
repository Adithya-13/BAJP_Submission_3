package com.extcode.project.jetpacksubmission3.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.extcode.project.jetpacksubmission3.data.source.remote.RemoteDataSource
import com.extcode.project.jetpacksubmission3.utils.DataDummy
import com.extcode.project.jetpacksubmission3.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieAppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieAppRepository = FakeMovieAppRepository(remote)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].id.toString()
    private val tvShowResponses = DataDummy.generateRemoteDummyTvShows()
    private val tvShowId = tvShowResponses[0].id.toString()

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(movieAppRepository.getAllMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getTvShows(any())
        val tvShowEntities = LiveDataTestUtil.getValue(movieAppRepository.getAllTvShows())
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getMovieById() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getMovies(any())
        val movieEntity = LiveDataTestUtil.getValue(movieAppRepository.getMovieById(movieId))
        val movieResponse = movieResponses[0]
        verify(remote).getMovies(any())
        assertNotNull(movieEntity)
        assertEquals(movieResponse.title, movieEntity.title)
        assertEquals(movieResponse.releaseDate, movieEntity.releaseDate)
        assertEquals(movieResponse.overview, movieEntity.overview)
        assertEquals(movieResponse.originalLanguage, movieEntity.originalLanguage)
        assertEquals(movieResponse.id, movieEntity.id)
        assertEquals(movieResponse.popularity, movieEntity.popularity, movieResponse.popularity)
        assertEquals(movieResponse.voteAverage, movieEntity.voteAverage, movieResponse.voteAverage)
        assertEquals(movieResponse.voteCount, movieEntity.voteCount)
        assertEquals(movieResponse.posterPath, movieEntity.posterPath)
    }

    @Test
    fun getTvShowById() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getTvShows(any())
        val tvShowEntity = LiveDataTestUtil.getValue(movieAppRepository.getTvShowById(tvShowId))
        val tvShowResponse = tvShowResponses[0]
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntity)
        assertEquals(tvShowResponse.name, tvShowEntity.title)
        assertEquals(tvShowResponse.firstAirDate, tvShowEntity.releaseDate)
        assertEquals(tvShowResponse.overview, tvShowEntity.overview)
        assertEquals(tvShowResponse.originalLanguage, tvShowEntity.originalLanguage)
        assertEquals(tvShowResponse.id, tvShowEntity.id)
        assertEquals(tvShowResponse.popularity, tvShowEntity.popularity, tvShowResponse.popularity)
        assertEquals(
            tvShowResponse.voteAverage,
            tvShowEntity.voteAverage,
            tvShowResponse.voteAverage
        )
        assertEquals(tvShowResponse.voteCount, tvShowEntity.voteCount)
        assertEquals(tvShowResponse.posterPath, tvShowEntity.posterPath)
    }
}