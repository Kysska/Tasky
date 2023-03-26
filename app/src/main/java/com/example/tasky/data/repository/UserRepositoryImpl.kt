package com.example.tasky.data.repository

import android.content.Context
import com.example.tasky.data.UserData
import com.example.tasky.data.storage.UserStorage
import com.example.tasky.domain.repository.UserRepository


class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun saveName(saveParam: UserData): Boolean{
        val result = userStorage.save(saveParam)
        return result
    }

    override fun getName(): UserData {
        val user = userStorage.get()
        return user
    }

}