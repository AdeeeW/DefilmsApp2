package com.adewijayanto.defilmsapp2.ui.detailtvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adewijayanto.defilmsapp2.data.CatalogueRepository
import com.adewijayanto.defilmsapp2.data.source.localentity.TvShowEntity
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
class DetailTvShowActivityViewModelTest {
    private lateinit var viewModel: DetailTvShowActivityViewModel
    private val dummyTvShows = DataDummy.generateDummyTvShow()[0]
    private val tvshowId = dummyTvShows.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: CatalogueRepository

    @Mock
    private lateinit var tvShowDetailObserver: Observer<TvShowEntity>

    @Before
    fun setup(){
        viewModel = DetailTvShowActivityViewModel(tvShowRepository)
        viewModel.setSelectedTvShow(tvshowId)
    }

    @Test
    fun getTvShowsDetail() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShows

        Mockito.`when`(tvShowRepository.loadDetailTvShows(tvshowId)).thenReturn(tvShow)
        viewModel.setSelectedTvShow(dummyTvShows.id)
        val tvShowEntity = viewModel.getTvShowsDetail().value as TvShowEntity
        Mockito.verify(tvShowRepository).loadDetailTvShows(tvshowId)
        assertNotNull(tvShowEntity)

        dummyTvShows.apply {
            assertEquals(id,tvShowEntity.id)
            assertEquals(first_air_date,tvShowEntity.first_air_date)
            assertEquals(name,tvShowEntity.name)
            assertEquals(overview,tvShowEntity.overview)
            assertEquals(poster_path,tvShowEntity.poster_path)
            assertEquals(original_language,tvShowEntity.original_language)
            assertEquals(origin_country,tvShowEntity.origin_country)
            assertEquals(vote_average,tvShowEntity.vote_average)
        }
        viewModel.getTvShowsDetail().observeForever(tvShowDetailObserver)
        Mockito.verify(tvShowDetailObserver).onChanged(dummyTvShows)
    }
}