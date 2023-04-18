package com.example.tasky.data.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tasky.data.UserData

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(userData: UserData)

    @Query("Select * from users_table")
    fun getUser(): LiveData<List<UserData>>

    @Query("Select * from users_table WHERE name = :login")
    fun getUserName(login: String): UserData?

    @Query("SELECT COUNT(*) FROM users_table")
    fun getUserCount(): Int

}