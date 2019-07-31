package com.wattpad.ca.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wattpad.ca.dao.StoryDAO
import com.wattpad.ca.dao.UserDAO
import com.wattpad.ca.pojo.Story
import com.wattpad.ca.pojo.User

@Database(entities = arrayOf(Story::class, User::class), version = 1)
abstract class WattpadDatabase : RoomDatabase() {

    abstract fun storyDao(): StoryDAO

    abstract fun userDao(): UserDAO

    companion object {
        private var INSTANCE: WattpadDatabase? = null
        fun getDatabase(context: Context): WattpadDatabase? {
            if (INSTANCE == null) {
                synchronized(WattpadDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        WattpadDatabase::class.java, "wattpad.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}