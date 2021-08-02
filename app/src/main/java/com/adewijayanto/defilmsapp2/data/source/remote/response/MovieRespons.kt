package com.adewijayanto.defilmsapp2.data.source.remote.response

import android.os.Parcelable
import com.adewijayanto.defilmsapp2.data.source.localentity.MovieEntity
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class MovieRespons(
        val results: ArrayList<MovieEntity>
): Parcelable