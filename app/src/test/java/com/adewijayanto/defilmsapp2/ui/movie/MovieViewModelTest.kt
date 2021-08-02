package com.adewijayanto.defilmsapp2.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adewijayanto.defilmsapp2.data.CatalogueRepository
import com.adewijayanto.defilmsapp2.data.source.localentity.MovieEntity
import com.adewijayanto.defilmsapp2.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setup(){
        viewModel = MovieViewModel(moviesRepository)
    }

    @Test
    fun getAllMovies() {
        val dummyMovies = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(moviesRepository.loadAllMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getAllMovies().value
        Mockito.verify(moviesRepository).loadAllMovies()
        assertNotNull(moviesEntities)
        assertEquals(moviesEntities?.size,movies.value!!.size)

        viewModel.getAllMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)
    }
}