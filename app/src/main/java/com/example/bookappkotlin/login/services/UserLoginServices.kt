package com.example.bookappkotlin.login.services

import com.example.bookappkotlin.login.model.User
import com.example.bookappkotlin.login.repository.LoginRepository
import com.example.bookappkotlin.login.repository.LoginResponse

interface LoginService {
    fun loginUser(user: User, response: LoginResponse)
    fun isLogged(): Boolean
}

class UserLoginServices(
    private val repository: LoginRepository
) : LoginService {

    override fun loginUser(user: User, response: LoginResponse) {
        return repository.loginUser(user, response)
    }

    override fun isLogged(): Boolean {
        return repository.isLogged()
    }
}