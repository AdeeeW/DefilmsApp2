package com.adewijayanto.defilmsapp2.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adewijayanto.defilmsapp2.data.CatalogueRepository
import com.adewijayanto.defilmsapp2.data.source.localentity.TvShowEntity
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setup(){
        viewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun getAllTvShow() {
        val dummyTvShow = DataDummy.generateDummyTvShow()
        val tvShow = MutableLiveData<List<TvShowEntity>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(tvShowRepository.loadAllTvShows()).thenReturn(tvShow)
        val tvShowEntities = viewModel.getAllTvShow().value
        Mockito.verify(tvShowRepository).loadAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowEntities?.size,tvShow.value!!.size)

        viewModel.getAllTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)
    }
}