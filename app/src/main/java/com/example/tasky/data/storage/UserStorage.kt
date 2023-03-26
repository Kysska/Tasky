package com.example.tasky.data.storage

import com.example.tasky.data.UserData

interface UserStorage {
    fun save(saveParam: UserData):Boolean
    fun get(): UserData
}