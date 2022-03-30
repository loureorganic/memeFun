package com.example.bookappkotlin.screens.profile.repository

import android.annotation.SuppressLint
import com.example.bookappkotlin.repositories.helpper.AuthenticationHelper
import com.example.bookappkotlin.repositories.helpper.DatabaseAuthenticationHelper
import com.google.firebase.database.DataSnapshot
import io.reactivex.Observable
import org.koin.core.component.KoinComponent

interface RepositoryProfile {
    fun userData(): Observable<DataSnapshot>
}

class ProfileRepository(
) : RepositoryProfile, KoinComponent {

    private lateinit var databaseAuthenticationHelper: AuthenticationHelper

    @SuppressLint("CheckResult")
    override fun userData(): Observable<DataSnapshot> {
        databaseAuthenticationHelper = DatabaseAuthenticationHelper()
        return databaseAuthenticationHelper.userData()
    }
}