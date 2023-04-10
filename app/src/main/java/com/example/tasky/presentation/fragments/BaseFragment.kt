package com.example.tasky.presentation.fragments

import android.content.Context
import androidx.fragment.app.Fragment

abstract  class BaseFragment : Fragment(){
    abstract val applicationContext: Context

    abstract fun onClickNew()
}