package com.adewijayanto.defilmsapp2.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adewijayanto.defilmsapp2.data.CatalogueRepository
import com.adewijayanto.defilmsapp2.data.source.localentity.MovieEntity

class DetailMovieActivityViewModel(private val moviesRepository: CatalogueRepository) :
    ViewModel() {

    private var movieId: Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getMovieDetail(): LiveData<MovieEntity> = moviesRepository.loadDetailMovies(movieId)

}