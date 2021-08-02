package com.adewijayanto.defilmsapp2.data.source.network

import com.adewijayanto.defilmsapp2.data.source.localentity.MovieEntity
import com.adewijayanto.defilmsapp2.data.source.localentity.TvShowEntity
import com.adewijayanto.defilmsapp2.data.source.remote.response.MovieRespons
import com.adewijayanto.defilmsapp2.data.source.remote.response.TvShowRespons
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    fun getMovies(
        @Query("api_key") api: String
    ): Call<MovieRespons>

    @GET("tv/top_rated")
    fun getTvShows(
        @Query("api_key") api: String
    ): Call<TvShowRespons>

    @GET("movie/{movie_id}")
    fun getDetailMovies(
        @Path("movie_id") id: Int,
        @Query("api_key") api: String
    ): Call<MovieEntity>

    @GET("tv/{tv_id}")
    fun getDetailTvShows(
        @Path("tv_id") id: Int,
        @Query("api_key") api: String
    ): Call<TvShowEntity>
}