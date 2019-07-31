package com.wattpad.ca.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wattpad.ca.pojo.User

@Dao
interface UserDAO {
    @Insert
    fun insert(user: User)
    @Query("SELECT * FROM tb_user ORDER BY name ASC")
    fun getAllUsers(): List<User>
}