package com.example.bookappkotlin.login.services

import com.example.bookappkotlin.login.model.UserLogin
import com.example.bookappkotlin.login.repository.LoginRepository
import com.example.bookappkotlin.login.repository.LoginResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface LoginService {
    fun loginUser(userLogin: UserLogin, response: LoginResponse)
}

class UserLoginServices() : LoginService, KoinComponent {

    private val repository by inject<LoginRepository>()

    override fun loginUser(userLogin: UserLogin, response: LoginResponse) {
        return repository.loginUser(userLogin, response)
    }

}