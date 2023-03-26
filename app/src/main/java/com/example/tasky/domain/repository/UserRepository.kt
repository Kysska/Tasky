package com.example.tasky.domain.repository

import com.example.tasky.data.UserData

interface UserRepository {

    fun saveName(saveParam: UserData): Boolean
    fun getName(): UserData
}