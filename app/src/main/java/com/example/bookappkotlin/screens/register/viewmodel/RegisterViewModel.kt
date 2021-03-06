package com.example.bookappkotlin.screens.register.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookappkotlin.screens.register.model.UserRegister
import com.example.bookappkotlin.screens.register.services.RegisterService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ViewModelRegister {
    val booleanCreateAccountLiveData: MutableLiveData<Boolean>
    val errorCreateAccountLiveData: MutableLiveData<Boolean>
    fun createUserAccount(user: UserRegister)
    fun dataValidation(user: UserRegister): String
}

class RegisterViewModel : ViewModel(), KoinComponent, ViewModelRegister {

    private val services by inject<RegisterService>()
    private val createAccountLiveData = MutableLiveData<Boolean>()
    override val booleanCreateAccountLiveData: MutableLiveData<Boolean> = createAccountLiveData

    private val errorAccountLiveData = MutableLiveData<Boolean>()
    override val errorCreateAccountLiveData: MutableLiveData<Boolean> = errorAccountLiveData


    override fun dataValidation(user: UserRegister): String {
        return services.dataValidation(user = user)
    }

    @SuppressLint("CheckResult")
    override fun createUserAccount(user: UserRegister) {
        val response = services.createUserAccount(user = user)
        response.subscribe({ result ->
            errorCreateAccountLiveData.postValue(false)
            booleanCreateAccountLiveData.postValue(result)
        }, { error ->
            errorCreateAccountLiveData.postValue(true)
            Log.e("ERROR", "RegisterViewModel $error")
        })
    }

}