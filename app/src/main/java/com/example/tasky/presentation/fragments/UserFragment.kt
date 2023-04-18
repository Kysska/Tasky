package com.example.tasky.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tasky.R
import com.example.tasky.data.UserData

class UserFragment() : BaseFragment() {
    override val applicationContext: Context
        get() = requireContext().applicationContext
    private lateinit var nameTextView: TextView
    private lateinit var userData: UserData
    override fun onClickNew() {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        nameTextView = view.findViewById(R.id.textUser)

        nameTextView.text = userData.login

        return view
    }
    companion object {
        @JvmStatic
        fun newInstance(): BaseFragment = UserFragment()
    }

}