package com.example.bookappkotlin.register.services

import com.example.bookappkotlin.register.model.User
import com.example.bookappkotlin.register.repository.RegisterRepository
import com.example.bookappkotlin.register.repository.RegisterResponse

interface RegisterService {
    fun createUserAccount(user: User, response: RegisterResponse)
    fun isLogged(): Boolean
}

class UserRegisterServices(
    private val repository: RegisterRepository
) : RegisterService {

    override fun createUserAccount(user: User, response: RegisterResponse) {
        return repository.createUserAccount(user, response)
    }

    override fun isLogged(): Boolean {
      return repository.isLogged()
    }
}