package com.example.bookappkotlin.screens.splash.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookappkotlin.screens.splash.services.SplashService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


interface ViewModelSplash {
    val booleanCheckUserLiveData: MutableLiveData<Boolean>
    val errorCheckUserLiveData: MutableLiveData<Boolean>
    fun checkUser()
}

class SplashViewModel : ViewModel(), KoinComponent, ViewModelSplash {

    private val services by inject<SplashService>()

    private var checkUserLiveData = MutableLiveData<Boolean>()
    override val booleanCheckUserLiveData: MutableLiveData<Boolean> = checkUserLiveData

    private val errCheckUserLiveData = MutableLiveData<Boolean>()
    override val errorCheckUserLiveData: MutableLiveData<Boolean> = errCheckUserLiveData

    @SuppressLint("CheckResult")
    override fun checkUser() {
        val result = services.checkUser()
        result.subscribe(
            { response ->
                errorCheckUserLiveData.postValue(false)
                booleanCheckUserLiveData.postValue(response)
            },
            { error ->
                errorCheckUserLiveData.postValue(true)
                Log.e("ERROR", "SplashViewModel $error")
            }
        )
    }
}