package com.example.tasky.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tasky.R
import com.example.tasky.databinding.ActivityMain2Binding
import com.example.tasky.presentation.fragments.FragmentManager
import com.example.tasky.presentation.fragments.NoteFragment
import com.example.tasky.presentation.fragments.UserFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavListener()
    }

    private fun setBottomNavListener(){
        binding.bNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.main->{
                    Log.d("MyLog", "main")
                }
                R.id.note->{
                    FragmentManager.setFragment(NoteFragment.newInstance(), this)
                }
                R.id.game->{
                    Log.d("MyLog", "game")
                }
                R.id.forum->{
                    Log.d("MyLog", "forum")
                }
                R.id.profile->{
                    FragmentManager.setFragment(UserFragment.newInstance(), this)
                }
            }
            true
        }
    }
}