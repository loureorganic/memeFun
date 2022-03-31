package com.example.bookappkotlin.screens.profile.repository

import android.annotation.SuppressLint
import com.example.bookappkotlin.repositories.helpper.AuthenticationHelper
import com.example.bookappkotlin.repositories.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.screens.profile.model.UserData
import io.reactivex.Observable
import org.koin.core.component.KoinComponent

interface RepositoryProfile {
    fun userData(): Observable<UserData>
}

class ProfileRepository: RepositoryProfile, KoinComponent {

    private lateinit var databaseAuthenticationHelper: AuthenticationHelper

    @SuppressLint("CheckResult")
    override fun userData(): Observable<UserData> {
        databaseAuthenticationHelper = DatabaseAuthenticationHelper()
        return databaseAuthenticationHelper.userData()
    }
}