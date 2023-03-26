package com.example.tasky.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tasky.R

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this, MainViewModelFactory(this)).get(MainViewModel::class.java)

        val nameTextView = findViewById<TextView>(R.id.editTextPersonName)
        val passwordTextView = findViewById<TextView>(R.id.editTextPassword)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val textName = findViewById<TextView>(R.id.textName)

        vm.resultLive.observe(this, Observer{
            textName.text = it
        })

        saveButton.setOnClickListener(){
           val loginText = nameTextView.text.toString()
            val passwordText = passwordTextView.text.toString()
            vm.save(loginText, passwordText)
        }
    }
}