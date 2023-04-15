package com.example.tasky.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tasky.data.UserData
import com.example.tasky.data.repository.UserDao


@Database(entities = arrayOf(UserData::class), version = 1, exportSchema = false)
abstract  class UserDatabase: RoomDatabase() {

    abstract fun getUserDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE : UserDatabase? = null

        fun getDatabase(context: Context) : UserDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "users_table"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }

}