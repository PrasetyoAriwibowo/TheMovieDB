package com.bootcamp.entity.genre

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListGenre(
    val listGenre: List<Int>
): Parcelable