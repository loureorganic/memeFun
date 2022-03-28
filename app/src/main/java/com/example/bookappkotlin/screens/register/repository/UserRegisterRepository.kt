package com.example.bookappkotlin.screens.register.repository

import com.example.bookappkotlin.repositories.helpper.AuthenticationHelper
import com.example.bookappkotlin.repositories.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.screens.register.model.UserRegister
import io.reactivex.Observable
import org.koin.core.component.KoinComponent

interface RegisterRepository {
    fun createUserAccount(user: UserRegister): Observable<Boolean>
}

class UserRegisterRepository : RegisterRepository, KoinComponent {

    private lateinit var databaseAuthenticationHelper: AuthenticationHelper

    override fun createUserAccount(user: UserRegister): Observable<Boolean> {
        databaseAuthenticationHelper = DatabaseAuthenticationHelper()
        return databaseAuthenticationHelper.createUserAccount(user = user)
    }
}