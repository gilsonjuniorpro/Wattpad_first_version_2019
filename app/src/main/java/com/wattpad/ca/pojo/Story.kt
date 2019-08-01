package com.wattpad.ca.pojo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wattpad.ca.util.Constants

@Entity(tableName = Constants.TABLE_STORY)
data class Story(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @Embedded
    val user: User,
    @ColumnInfo(name = "cover") val cover: String,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "votecount") val voteCount: Int,
    @ColumnInfo(name = "description") val description: String
)