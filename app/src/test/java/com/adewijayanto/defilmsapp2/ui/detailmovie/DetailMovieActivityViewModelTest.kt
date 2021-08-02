package com.adewijayanto.defilmsapp2.ui.detailmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adewijayanto.defilmsapp2.data.CatalogueRepository
import com.adewijayanto.defilmsapp2.data.source.localentity.MovieEntity
import com.adewijayanto.defilmsapp2.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieActivityViewModelTest {
    private lateinit var viewModel: DetailMovieActivityViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: CatalogueRepository

    @Mock
    private lateinit var movieDetailObserver: Observer<MovieEntity>


    @Before
    fun setup(){
        viewModel = DetailMovieActivityViewModel(movieRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getTvShowsDetail() {
        val tvShow = MutableLiveData<MovieEntity>()
        tvShow.value = dummyMovie

        Mockito.`when`(movieRepository.loadDetailMovies(movieId)).thenReturn(tvShow)
        viewModel.setSelectedMovie(dummyMovie.id)
        val movieEntity = viewModel.getMovieDetail().value as MovieEntity
        Mockito.verify(movieRepository).loadDetailMovies(movieId)
        assertNotNull(movieEntity)

        dummyMovie.apply {
            assertEquals(id,movieEntity.id)
            assertEquals(release_date,movieEntity.release_date)
            assertEquals(title,movieEntity.title)
            assertEquals(overview,movieEntity.overview)
            assertEquals(poster_path,movieEntity.poster_path)
            assertEquals(original_language,movieEntity.original_language)
            assertEquals(vote_average,movieEntity.vote_average)
        }
        viewModel.getMovieDetail().observeForever(movieDetailObserver)
        Mockito.verify(movieDetailObserver).onChanged(dummyMovie)
    }
}