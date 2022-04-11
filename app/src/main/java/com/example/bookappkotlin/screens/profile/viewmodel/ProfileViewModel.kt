package com.example.bookappkotlin.screens.profile.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookappkotlin.screens.profile.model.UserData
import com.example.bookappkotlin.screens.profile.services.ServiceProfile
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ViewModelProfile{
    val errorDataProfileAccount : MutableLiveData<Boolean>
    val dataProfileAccountLiveData : MutableLiveData<UserData>
    fun userData()
}

class ProfileViewModel : ViewModel(), ViewModelProfile, KoinComponent {

    private val loginAccountLiveData = MutableLiveData<UserData>()
    override val dataProfileAccountLiveData : MutableLiveData<UserData> = loginAccountLiveData

    private val errorDataProfile = MutableLiveData<Boolean>()
    override val errorDataProfileAccount : MutableLiveData<Boolean> = errorDataProfile

    private val services by inject<ServiceProfile>()

    @SuppressLint("CheckResult")
    override fun userData() {
        val result = services.userData()

        result.subscribe({ userData ->
            if (userData != null) {
                dataProfileAccountLiveData.postValue(userData)
                errorDataProfileAccount.postValue(false)
            }},
        { error ->
            errorDataProfileAccount.postValue(true)
            Log.e("ERROR", "ProfileViewModel $error")
        })
    }
}