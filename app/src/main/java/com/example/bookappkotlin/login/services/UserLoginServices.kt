package com.example.bookappkotlin.login.services

import com.example.bookappkotlin.login.model.User
import com.example.bookappkotlin.login.repository.LoginRepository
import com.example.bookappkotlin.login.repository.LoginResponse
import com.example.bookappkotlin.register.repository.RegisterRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface LoginService {
    fun loginUser(user: User, response: LoginResponse)
}

class UserLoginServices() : LoginService, KoinComponent {

    private val repository by inject<LoginRepository>()

    override fun loginUser(user: User, response: LoginResponse) {
        return repository.loginUser(user, response)
    }

}