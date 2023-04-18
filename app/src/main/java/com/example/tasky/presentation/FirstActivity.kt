package com.example.tasky.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.example.tasky.data.Note
import com.example.tasky.data.UserData
import com.example.tasky.data.db.NoteDatabase
import com.example.tasky.data.db.UserDatabase
import com.example.tasky.data.repository.NoteRepositoryImpl
import com.example.tasky.data.repository.UserRepositoryImpl
import com.example.tasky.presentation.user.LogInActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstActivity : AppCompatActivity() {

    private lateinit var database: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            if (isUserRegistered()) {
                // Пользователь зарегистрирован, перенаправляем на главную страницу
                startActivity(Intent(this@FirstActivity, MainActivity::class.java))
                finish()
            } else {
                // Пользователь не зарегистрирован, отправляем на экран с регистрацией
                startActivity(Intent(this@FirstActivity, LogInActivity::class.java))
                finish()
            }
        }
    }
    private suspend fun isUserRegistered(): Boolean {
        return withContext(Dispatchers.IO) {
            val dao = UserDatabase.getDatabase(application).getUserDao()
            dao.getUserCount() > 0
        }
    }

}