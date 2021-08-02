package com.adewijayanto.defilmsapp2.data.source.localentity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class TvShowEntity(
	val first_air_date: String,
	val overview: String,
	val original_language: String,
	val genre_ids: List<Int>,
	val genres: List<GenreEntity>,
	val poster_path: String,
	val origin_country: List<String>,
	val original_name: String,
	val popularity: Double,
	val vote_average: Float,
	val name: String,
	val id: Int,
	val vote_count: Int
) : Parcelable

