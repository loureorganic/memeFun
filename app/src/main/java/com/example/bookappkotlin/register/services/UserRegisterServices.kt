package com.example.bookappkotlin.register.services

import com.example.bookappkotlin.register.model.UserRegister
import com.example.bookappkotlin.register.repository.RegisterRepository
import com.example.bookappkotlin.register.repository.RegisterResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RegisterService {
    fun createUserAccount(user: UserRegister, response: RegisterResponse)
}

class UserRegisterServices() : RegisterService, KoinComponent {

    private val repository by inject<RegisterRepository>()

    override fun createUserAccount(user: UserRegister, response: RegisterResponse) {
        return repository.createUserAccount(user, response)
    }
}