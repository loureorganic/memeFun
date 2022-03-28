package com.example.bookappkotlin.screens.splash.repository

import com.example.bookappkotlin.repositories.helpper.AuthenticationHelper
import com.example.bookappkotlin.repositories.helpper.DatabaseAuthenticationHelper
import io.reactivex.Observable
import org.koin.core.component.KoinComponent


interface RepositorySplash {
    fun checkUser(): Observable<Boolean>
}

class SplashRepository : RepositorySplash, KoinComponent {

    lateinit var databaseAuthenticationHelper : AuthenticationHelper

    override fun checkUser() : Observable<Boolean> {
        databaseAuthenticationHelper = DatabaseAuthenticationHelper()
        return databaseAuthenticationHelper.checkUser()
    }
}