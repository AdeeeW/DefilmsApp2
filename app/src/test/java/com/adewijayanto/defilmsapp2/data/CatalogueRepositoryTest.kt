package com.adewijayanto.defilmsapp2.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adewijayanto.defilmsapp2.data.source.remote.RemoteDataSource
import com.adewijayanto.defilmsapp2.utils.DataDummy
import com.adewijayanto.defilmsapp2.utils.LiveDataTestUtil
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CatalogueRepositoryTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteRepository = Mockito.mock(RemoteDataSource::class.java)
    private val dataRepositoryTest = FakeCatalogueRepository(remoteRepository)

    private val moviesResponse = DataDummy.generateDummyMovies()
    private val moviesId = moviesResponse[0].id
    private val tvShwosResponse = DataDummy.generateDummyTvShow()
    private val tvShowsId = tvShwosResponse[0].id

    private fun <T> anyOfT(type: Class<T>): T = Mockito.any(type)

    private fun <T> eqOfT(obj: T): T = Mockito.eq(obj)

    @Test
    fun loadAllMovies() {
        Mockito.doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.GetMovieCallback
            callback.onResponse(moviesResponse)
            null
        }.`when`(remoteRepository).loadMovieApi(anyOfT(RemoteDataSource.GetMovieCallback::class.java))

        val result = LiveDataTestUtil.getValue(dataRepositoryTest.loadAllMovies())
        assertEquals(moviesResponse.size, result.size)
    }

    @Test
    fun loadAllTvShows() {
        Mockito.doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.GetTvShowCallback
            callback.onResponse(tvShwosResponse)
            null
        }.`when`(remoteRepository).loadTvShowApi(anyOfT(RemoteDataSource.GetTvShowCallback::class.java))

        val result = LiveDataTestUtil.getValue(dataRepositoryTest.loadAllTvShows())
        assertEquals(tvShwosResponse.size, result.size)
    }

    @Test
    fun loadDetailMovies() {
        Mockito.doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.GetMovieDetailCallback
            callback.onResponse(moviesResponse[0])
            null
        }.`when`(remoteRepository).loadMovieDetailApi(
            eqOfT(moviesId.toString()),
            anyOfT(RemoteDataSource.GetMovieDetailCallback::class.java))
    }

    @Test
    fun loadDetailTvShows() {
        Mockito.doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.GetTvShowDetailCallback
            callback.onResponse(tvShwosResponse[0])
            null
        }.`when`(remoteRepository).loadTvShowDetailApi(eqOfT(tvShowsId.toString()),
            anyOfT(RemoteDataSource.GetTvShowDetailCallback::class.java))
    }
}