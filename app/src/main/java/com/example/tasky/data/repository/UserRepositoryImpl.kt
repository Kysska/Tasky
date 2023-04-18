package com.example.tasky.data.repository

import android.accounts.AuthenticatorException
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import com.example.tasky.data.UserData


class UserRepositoryImpl(private val userDao: UserDao){

    val allUserData: LiveData<List<UserData>> = userDao.getUser()

    suspend fun saveName(userData: UserData){
        userDao.save(userData)
    }

    suspend fun getUserName(login: String, password: String): Boolean{
        val tuple = userDao.getUserName(login) ?: return false
        if(tuple.password != password) return false
        return true
    }

}