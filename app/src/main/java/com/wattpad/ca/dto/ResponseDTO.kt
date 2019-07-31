package com.wattpad.ca.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseDTO (
    val stories : List<StoryDTO>?,
    val nextUrl : String
): Parcelable