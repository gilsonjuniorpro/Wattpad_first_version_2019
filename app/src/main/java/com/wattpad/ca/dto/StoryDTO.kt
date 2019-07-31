package com.wattpad.ca.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoryDTO (
    var id: String?,
    var title : String?,
    var user : UserDTO?,
    var cover : String?
): Parcelable