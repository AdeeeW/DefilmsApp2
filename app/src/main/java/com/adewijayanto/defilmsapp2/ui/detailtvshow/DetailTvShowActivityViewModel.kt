package com.adewijayanto.defilmsapp2.ui.detailtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adewijayanto.defilmsapp2.data.CatalogueRepository
import com.adewijayanto.defilmsapp2.data.source.localentity.TvShowEntity

class DetailTvShowActivityViewModel(private val tvShowRepository: CatalogueRepository) :
    ViewModel() {

    private var tvShowId: Int = 0

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getTvShowsDetail(): LiveData<TvShowEntity> = tvShowRepository.loadDetailTvShows(tvShowId)

}