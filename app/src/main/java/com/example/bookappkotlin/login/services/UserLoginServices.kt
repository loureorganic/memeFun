package com.example.bookappkotlin.login.services

import com.example.bookappkotlin.login.model.UserLogin
import com.example.bookappkotlin.login.repository.LoginRepository
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface LoginService {
    fun loginUser(userLogin: UserLogin) : Observable<Boolean>
}

class UserLoginServices() : LoginService, KoinComponent {

    private val repository by inject<LoginRepository>()

    override fun loginUser(userLogin: UserLogin) : Observable<Boolean> {
        return repository.loginUser(userLogin)
    }
}