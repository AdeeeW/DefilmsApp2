package com.adewijayanto.defilmsapp2.data.source.localentity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class GenreEntity(
        val id: Int,
        val name: String
) : Parcelable