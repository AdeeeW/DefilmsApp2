package com.adewijayanto.defilmsapp2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adewijayanto.defilmsapp2.data.CatalogueRepository
import com.adewijayanto.defilmsapp2.di.Injection
import com.adewijayanto.defilmsapp2.ui.detailmovie.DetailMovieActivityViewModel
import com.adewijayanto.defilmsapp2.ui.detailtvshow.DetailTvShowActivityViewModel
import com.adewijayanto.defilmsapp2.ui.movie.MovieViewModel
import com.adewijayanto.defilmsapp2.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieActivityViewModel::class.java) -> {
                DetailMovieActivityViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvShowActivityViewModel::class.java) -> {
                DetailTvShowActivityViewModel(catalogueRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}

