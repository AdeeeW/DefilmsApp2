package com.adewijayanto.defilmsapp2.data

import androidx.lifecycle.LiveData
import com.adewijayanto.defilmsapp2.data.source.localentity.MovieEntity
import com.adewijayanto.defilmsapp2.data.source.localentity.TvShowEntity

interface DataSource {
    fun loadAllMovies(): LiveData<List<MovieEntity>>

    fun loadAllTvShows(): LiveData<List<TvShowEntity>>

    fun loadDetailMovies(movieId: Int): LiveData<MovieEntity>

    fun loadDetailTvShows(tvShowId: Int): LiveData<TvShowEntity>
}