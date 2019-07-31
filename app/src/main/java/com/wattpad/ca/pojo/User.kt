package com.wattpad.ca.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wattpad.ca.util.Constants

@Entity(tableName = Constants.TABLE_USER)
data class User(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "avatar") val avatar: String,
    @ColumnInfo(name = "fullname") val fullname: String

)