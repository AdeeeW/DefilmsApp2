package com.adewijayanto.defilmsapp2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adewijayanto.defilmsapp2.data.source.localentity.MovieEntity
import com.adewijayanto.defilmsapp2.data.source.localentity.TvShowEntity
import com.adewijayanto.defilmsapp2.data.source.remote.RemoteDataSource

class FakeCatalogueRepository(private val remoteDataSource: RemoteDataSource) : DataSource {

    override fun loadAllMovies(): LiveData<List<MovieEntity>> {
        val movieLists = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.loadMovieApi(object: RemoteDataSource.GetMovieCallback{
            override fun onResponse(movieResponse: List<MovieEntity>) {
                movieLists.postValue(movieResponse)
            }
        })
        return movieLists
    }

    override fun loadAllTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowList = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.loadTvShowApi(object: RemoteDataSource.GetTvShowCallback{
            override fun onResponse(tvShowResponse: List<TvShowEntity>) {
                tvShowList.postValue(tvShowResponse)
            }
        })
        return tvShowList
    }

    override fun loadDetailMovies(movieId: Int): LiveData<MovieEntity> {
        val movieDetail = MutableLiveData<MovieEntity>()
        remoteDataSource.loadMovieDetailApi(movieId.toString(), object: RemoteDataSource.GetMovieDetailCallback{
            override fun onResponse(movieDetailResponse: MovieEntity) {
                movieDetail.postValue(movieDetailResponse)
            }

        })
        return movieDetail
    }

    override fun loadDetailTvShows(tvShowId: Int): LiveData<TvShowEntity> {
        val tvShowDetail = MutableLiveData<TvShowEntity>()
        remoteDataSource.loadTvShowDetailApi(tvShowId.toString(), object: RemoteDataSource.GetTvShowDetailCallback{
            override fun onResponse(tvShowDetailResponse: TvShowEntity) {
                tvShowDetail.postValue(tvShowDetailResponse)
            }

        })
        return tvShowDetail
    }


}
