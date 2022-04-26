package com.example.bookappkotlin.screens.login.services

import android.util.Patterns
import com.example.bookappkotlin.screens.login.model.UserLogin
import com.example.bookappkotlin.screens.login.repository.LoginRepository
import com.example.bookappkotlin.screens.login.utils.LoginConstants
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface LoginService {
    fun loginUser(userLogin: UserLogin) : Observable<Boolean>
    fun dataValidation (user: UserLogin): String
}

class UserLoginServices : LoginService, KoinComponent {

    private val repository by inject<LoginRepository>()

    override fun dataValidation(user: UserLogin): String {
        return  if (user.password.isEmpty() && user.password.length >= 6) {
            LoginConstants.EMPTY_PASSWORD
        }else if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            LoginConstants.INVALID_EMAIL
        }  else {
            LoginConstants.VALID
        }
    }

    override fun loginUser(userLogin: UserLogin) : Observable<Boolean> {
        return repository.loginUser(userLogin)
    }

}