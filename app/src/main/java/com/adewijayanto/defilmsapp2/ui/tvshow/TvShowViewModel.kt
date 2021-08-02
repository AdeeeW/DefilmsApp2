package com.adewijayanto.defilmsapp2.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adewijayanto.defilmsapp2.data.CatalogueRepository
import com.adewijayanto.defilmsapp2.data.source.localentity.TvShowEntity

class TvShowViewModel(private val tvShowRepository: CatalogueRepository) : ViewModel() {
    fun getAllTvShow(): LiveData<List<TvShowEntity>> = tvShowRepository.loadAllTvShows()
}