package com.adewijayanto.defilmsapp2.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adewijayanto.defilmsapp2.data.CatalogueRepository
import com.adewijayanto.defilmsapp2.data.source.localentity.MovieEntity

class MovieViewModel(private val moviesRepository: CatalogueRepository) : ViewModel() {
    fun getAllMovies(): LiveData<List<MovieEntity>> = moviesRepository.loadAllMovies()
}