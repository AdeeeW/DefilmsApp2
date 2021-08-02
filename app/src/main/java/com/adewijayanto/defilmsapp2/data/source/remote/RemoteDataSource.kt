package com.adewijayanto.defilmsapp2.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.adewijayanto.defilmsapp2.BuildConfig
import com.adewijayanto.defilmsapp2.data.source.localentity.MovieEntity
import com.adewijayanto.defilmsapp2.data.source.localentity.TvShowEntity
import com.adewijayanto.defilmsapp2.data.source.network.ApiConfig
import com.adewijayanto.defilmsapp2.data.source.remote.response.MovieRespons
import com.adewijayanto.defilmsapp2.data.source.remote.response.TvShowRespons
import com.adewijayanto.defilmsapp2.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(retrofitConfig: ApiConfig) {
    private val apiKey = BuildConfig.API_KEY
    private var handler = Handler(Looper.getMainLooper())
    private val retrofitConfig = ApiConfig

    companion object{
        private var INSTANCE: RemoteDataSource? = null
        private val TAG = RemoteDataSource::class.java.toString()
        private const val TIME_IN_MILLIS: Long = 1500

        fun getInstance(retrofitConfig: ApiConfig):RemoteDataSource{
            if (INSTANCE == null)
                INSTANCE = RemoteDataSource(retrofitConfig)
            return INSTANCE!!
        }
    }

    fun loadMovieApi(getMovieCallback: GetMovieCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            retrofitConfig.getApiService().getMovies(apiKey).enqueue(object: Callback<MovieRespons>{
                override fun onFailure(call: Call<MovieRespons>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                }

                override fun onResponse(
                    call: Call<MovieRespons>,
                    response: Response<MovieRespons>
                ) {
                    response.body()?.results?.let { getMovieCallback.onResponse(it) }
                    EspressoIdlingResource.decrement()
                }

            })
        }, TIME_IN_MILLIS)
    }

    fun loadMovieDetailApi(movieId: String, getMovieDetailCallback: GetMovieDetailCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            retrofitConfig.getApiService().getDetailMovies(movieId.toInt(), apiKey).enqueue(object: Callback<MovieEntity>{
                override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                }

                override fun onResponse(call: Call<MovieEntity>, response: Response<MovieEntity>) {
                    getMovieDetailCallback.onResponse(response.body()!!)
                    EspressoIdlingResource.decrement()
                }

            })
        }, TIME_IN_MILLIS)
    }

    fun loadTvShowApi(getTvShowCallback: GetTvShowCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            retrofitConfig.getApiService().getTvShows(apiKey).enqueue(object: Callback<TvShowRespons>{
                override fun onFailure(call: Call<TvShowRespons>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                }

                override fun onResponse(
                    call: Call<TvShowRespons>,
                    response: Response<TvShowRespons>
                ) {
                    response.body()?.results?.let { getTvShowCallback.onResponse(it) }
                    EspressoIdlingResource.decrement()
                }

            })
        }, TIME_IN_MILLIS)
    }

    fun loadTvShowDetailApi(tvShowId: String, getTvShowDetailCallback: GetTvShowDetailCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            retrofitConfig.getApiService().getDetailTvShows(tvShowId.toInt(), apiKey).enqueue(object: Callback<TvShowEntity>{
                override fun onFailure(call: Call<TvShowEntity>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                }

                override fun onResponse(
                    call: Call<TvShowEntity>,
                    response: Response<TvShowEntity>
                ) {
                    getTvShowDetailCallback.onResponse(response.body()!!)
                    EspressoIdlingResource.decrement()
                }

            })
        }, TIME_IN_MILLIS)
    }


    interface GetMovieCallback{
        fun onResponse(movieResponse: List<MovieEntity>)
    }
    interface GetMovieDetailCallback{
        fun onResponse(movieDetailResponse: MovieEntity)
    }

    interface GetTvShowCallback{
        fun onResponse(tvShowResponse: List<TvShowEntity>)
    }

    interface GetTvShowDetailCallback{
        fun onResponse(tvShowDetailResponse: TvShowEntity)
    }
}