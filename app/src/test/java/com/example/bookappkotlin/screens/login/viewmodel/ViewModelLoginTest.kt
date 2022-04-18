package com.example.bookappkotlin.screens.login.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookappkotlin.screens.login.model.UserLogin
import com.example.bookappkotlin.screens.login.services.LoginService

class ViewModelLoginTest {

    private val loginAccountLiveData = MutableLiveData<Boolean>()
     val booleanLoginAccountLiveData: MutableLiveData<Boolean> = loginAccountLiveData

    private val errLoginAccountLiveData = MutableLiveData<Boolean>()
    val errorLoginAccountLiveData: MutableLiveData<Boolean> = errLoginAccountLiveData

    @SuppressLint("CheckResult")
    fun loginUser(userLogin: UserLogin) {
            errLoginAccountLiveData.postValue(false)
            booleanLoginAccountLiveData.postValue(true)
    }
}