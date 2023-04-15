package com.example.tasky.data.repository

import androidx.lifecycle.LiveData
import com.example.tasky.data.UserData


class UserRepositoryImpl(private val userDao: UserDao){

    val allUserData: LiveData<List<UserData>> = userDao.getUser()

    suspend fun saveName(userData: UserData){
        userDao.save(userData)
    }

}