package com.example.bookappkotlin.screens.login.repository

import com.example.bookappkotlin.repositories.helpper.AuthenticationHelper
import com.example.bookappkotlin.repositories.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.screens.login.model.UserLogin
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface LoginRepository {
    fun loginUser(userLogin: UserLogin) : Observable<Boolean>
}

class UserLoginRepository(
): LoginRepository, KoinComponent {

    lateinit var databaseAuthenticationHelper: AuthenticationHelper
    private val databaseAuthentication by inject<AuthenticationHelper>()

    override fun loginUser(userLogin: UserLogin) : Observable<Boolean> {
        databaseAuthenticationHelper = databaseAuthentication
        return databaseAuthenticationHelper.loginUser(user = userLogin)
    }

}