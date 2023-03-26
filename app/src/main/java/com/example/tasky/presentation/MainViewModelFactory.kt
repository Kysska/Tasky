package com.example.tasky.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasky.data.repository.UserRepositoryImpl
import com.example.tasky.data.storage.SharedPrefUserStorage
import com.example.tasky.domain.usecase.GetDataUserUseCase
import com.example.tasky.domain.usecase.SaveDataUserUseCase

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory{

    private val repository by lazy {  UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = context)) }
    private val saveDataUserUseCase by lazy { SaveDataUserUseCase(repository) }
    private val getDataUserUseCase by lazy { GetDataUserUseCase(repository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            saveDataUserUseCase, getDataUserUseCase
        ) as T
    }
}