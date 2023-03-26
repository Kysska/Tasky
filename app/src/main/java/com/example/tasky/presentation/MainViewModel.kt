package com.example.tasky.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasky.data.UserData
import com.example.tasky.domain.usecase.GetDataUserUseCase
import com.example.tasky.domain.usecase.SaveDataUserUseCase

class MainViewModel(private val saveDataUserUseCase: SaveDataUserUseCase,
                    private val getDataUserUseCase: GetDataUserUseCase
                    ): ViewModel() {


    var resultLive = MutableLiveData<String>()

    init{

    }

    override fun onCleared() {
        super.onCleared()
    }

    fun save(loginText: String, passwordText: String){
        val params = UserData(login = loginText, password = passwordText)
        val result: Boolean = saveDataUserUseCase.execute(param = params)
        val userData: UserData = getDataUserUseCase.execute()
        resultLive.value = "${userData.login}  ${userData.password}"
    }

}