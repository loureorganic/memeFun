package com.example.bookappkotlin.splash.repository

import com.example.bookappkotlin.helpper.AuthenticationHelper
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
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