package com.example.tasky.presentation.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tasky.data.UserData
import com.example.tasky.data.db.UserDatabase
import com.example.tasky.data.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUpViewModel(application: Application)  : AndroidViewModel(application) {
    private val repository: UserRepositoryImpl
    val user: LiveData<List<UserData>>

    init{
        val dao = UserDatabase.getDatabase(application).getUserDao()
        repository = UserRepositoryImpl(dao)
        user = repository.allUserData
    }


    suspend fun save(loginText: String, passwordText: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext repository.getUserName(loginText, passwordText)
    }
    suspend fun insert(loginText: String, passwordText: String): Unit = withContext(Dispatchers.IO) {
        val user = UserData(id = null, login = loginText, password = passwordText)
        repository.saveName(user)
    }
}