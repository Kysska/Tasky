package com.example.tasky.presentation.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tasky.data.UserData
import com.example.tasky.data.db.UserDatabase
import com.example.tasky.data.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogInViewModel(application: Application)  : AndroidViewModel(application) {
    private val repository: UserRepositoryImpl
    val users: LiveData<List<UserData>>

    init{
        val dao = UserDatabase.getDatabase(application).getUserDao()
        repository = UserRepositoryImpl(dao)
        users = repository.allUserData
    }


    suspend fun save(loginText: String, passwordText: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext repository.getUserName(loginText, passwordText)
    }

}