package com.adewijayanto.defilmsapp2.data.source.localentity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
	val overview: String,
	val original_language: String,
	val original_title: String,
	val video: Boolean,
	val title: String,
	val genre_ids: List<Int>,
	val genres: List<GenreEntity>,
	val poster_path: String,
	val backdrop_path: String,
	val release_date: String,
	val popularity: Double,
	val vote_average: Float,
	val id: Int,
	val adult: Boolean,
	val vote_count: Int
) : Parcelable
