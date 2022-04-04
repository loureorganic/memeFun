package com.example.bookappkotlin.screens.splash.repository

import com.example.bookappkotlin.repositories.helpper.AuthenticationHelper
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


interface RepositorySplash {
    fun checkUser(): Observable<Boolean>
}

class SplashRepository : RepositorySplash, KoinComponent {

    private lateinit var databaseAuthenticationHelper : AuthenticationHelper
    private val databaseAuthentication by inject<AuthenticationHelper>()


    override fun checkUser() : Observable<Boolean> {
        databaseAuthenticationHelper = databaseAuthentication
        return databaseAuthenticationHelper.checkUser()
    }
}