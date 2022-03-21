package com.example.bookappkotlin.login.repository

import com.example.bookappkotlin.helpper.AuthenticationHelper
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.login.model.UserLogin
import io.reactivex.Observable
import org.koin.core.component.KoinComponent

interface LoginRepository {
    fun loginUser(userLogin: UserLogin) : Observable<Boolean>
}

class UserLoginRepository(
): LoginRepository, KoinComponent {

    lateinit var databaseAuthenticationHelper: AuthenticationHelper

    override fun loginUser(userLogin: UserLogin) : Observable<Boolean> {
        databaseAuthenticationHelper = DatabaseAuthenticationHelper()
        return databaseAuthenticationHelper.loginUser(user = userLogin)
    }

}