package com.wattpad.ca.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDTO (
    var name : String?,
    var avatar : String?,
    var fullname : String?
): Parcelable