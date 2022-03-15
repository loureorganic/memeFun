package com.example.bookappkotlin.register.services

import com.example.bookappkotlin.home.repository.HomeRepository
import com.example.bookappkotlin.register.model.User
import com.example.bookappkotlin.register.repository.RegisterRepository
import com.example.bookappkotlin.register.repository.RegisterResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RegisterService {
    fun createUserAccount(user: User, response: RegisterResponse)
}

class UserRegisterServices() : RegisterService, KoinComponent {

    private val repository by inject<RegisterRepository>()

    override fun createUserAccount(user: User, response: RegisterResponse) {
        return repository.createUserAccount(user, response)
    }
}