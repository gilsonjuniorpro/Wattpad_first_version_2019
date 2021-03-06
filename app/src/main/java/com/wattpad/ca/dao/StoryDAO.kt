package com.wattpad.ca.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wattpad.ca.pojo.Story

@Dao
interface StoryDAO {
    @Insert
    fun insert(story: Story)

    @Query("SELECT * FROM tb_story ORDER BY id ASC")
    fun getAllStories(): List<Story>

    @Query("DELETE FROM tb_story")
    fun deleteAll()

    @Query("SELECT * FROM tb_story limit 1")
    fun getLastStory(): Story
}



