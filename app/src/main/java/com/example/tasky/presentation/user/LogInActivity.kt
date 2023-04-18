package com.example.tasky.presentation.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.tasky.R
import com.example.tasky.data.UserData
import com.example.tasky.presentation.MainActivity
import kotlinx.coroutines.launch

class LogInActivity : AppCompatActivity() {

    private lateinit var vm: LogInViewModel
    private lateinit var user: UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        vm = ViewModelProvider(this@LogInActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(LogInViewModel::class.java)

        val nameTextView = findViewById<TextView>(R.id.editTextPersonName)
        val passwordTextView = findViewById<TextView>(R.id.editTextPassword)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val textName = findViewById<TextView>(R.id.signin)



        saveButton.setOnClickListener(){
            val loginText = nameTextView.text.toString()
            val passwordText = passwordTextView.text.toString()
            user = UserData(id = null, login = loginText, password = passwordText)
            lifecycleScope.launch {
                if (user.login == null || user.password == null) {
                    Toast.makeText(application, "Не верные параметры", Toast.LENGTH_SHORT)
                        .show()
                }
                else{
                    if (vm.save(loginText, passwordText)) {
                        startActivity(Intent(this@LogInActivity, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(application, "Неверный логин или пароль", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
        textName.setOnClickListener(){
            startActivity(Intent(this@LogInActivity, SignUpActivity::class.java))
            finish()
        }
    }
}