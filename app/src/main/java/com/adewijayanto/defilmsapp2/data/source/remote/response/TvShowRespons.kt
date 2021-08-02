package com.adewijayanto.defilmsapp2.data.source.remote.response

import android.os.Parcelable
import com.adewijayanto.defilmsapp2.data.source.localentity.TvShowEntity
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class TvShowRespons(
        val results: ArrayList<TvShowEntity>
): Parcelable