package com.example.bookappkotlin.profile.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.bookappkotlin.helpper.AuthenticationHelper
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.profile.model.UserData
import com.google.firebase.database.*
import io.reactivex.Observable
import org.koin.core.component.KoinComponent

interface RepositoryProfile {
    fun userData(): Observable<UserData>
}

class ProfileRepository(
) : RepositoryProfile, KoinComponent {

    private lateinit var databaseAuthenticationHelper: AuthenticationHelper

    @SuppressLint("CheckResult")
    override fun userData(): Observable<UserData> {
        databaseAuthenticationHelper = DatabaseAuthenticationHelper()
        return databaseAuthenticationHelper.userData()
    }
}