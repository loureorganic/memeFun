package com.example.bookappkotlin.screens.register.repository

import com.example.bookappkotlin.repositories.helpper.AuthenticationHelper
import com.example.bookappkotlin.screens.register.model.UserRegister
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RegisterRepository {
    fun createUserAccount(user: UserRegister): Observable<Boolean>
}

class UserRegisterRepository : RegisterRepository, KoinComponent {

    private lateinit var databaseAuthenticationHelper: AuthenticationHelper
    private val databaseAuthentication by inject<AuthenticationHelper>()

    override fun createUserAccount(user: UserRegister): Observable<Boolean> {
        databaseAuthenticationHelper = databaseAuthentication
        return databaseAuthenticationHelper.createUserAccount(user = user)
    }
}