package com.example.bookappkotlin.register.services

import com.example.bookappkotlin.register.model.UserRegister
import com.example.bookappkotlin.register.repository.RegisterRepository
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RegisterService {
    fun createUserAccount(user: UserRegister): Observable<Boolean>
}

class UserRegisterServices() : RegisterService, KoinComponent {

    private val repository by inject<RegisterRepository>()

    override fun createUserAccount(user: UserRegister): Observable<Boolean> {
        return repository.createUserAccount(user)
    }
}