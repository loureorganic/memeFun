package com.example.bookappkotlin.screens.login.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookappkotlin.screens.login.model.UserLogin
import com.example.bookappkotlin.screens.login.services.LoginService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface  ViewModelLogin {
    val booleanLoginAccountLiveData : MutableLiveData<Boolean>
    fun loginUser(userLogin: UserLogin)
    fun dataValidation (user: UserLogin): String
}

class LoginViewModel : ViewModel(), ViewModelLogin, KoinComponent{

    private val services by inject<LoginService>()
    private val loginAccountLiveData = MutableLiveData<Boolean>()
    override val booleanLoginAccountLiveData: MutableLiveData<Boolean> = loginAccountLiveData

    @SuppressLint("CheckResult")
    override fun loginUser(userLogin: UserLogin) {
        val response = services.loginUser(userLogin = userLogin)
        response.subscribe ({ result ->
                booleanLoginAccountLiveData.postValue(result)
        },{error -> Log.e("ERROR", "LoginViewModel $error")
        })
    }

    override fun dataValidation(user: UserLogin): String {
        return services.dataValidation(user = user)
    }
}