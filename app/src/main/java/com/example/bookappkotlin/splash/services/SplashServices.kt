package com.example.bookappkotlin.splash.services

import com.example.bookappkotlin.register.model.User
import com.example.bookappkotlin.register.repository.RegisterRepository
import com.example.bookappkotlin.splash.repository.SplashRepository
import com.example.bookappkotlin.splash.repository.SplashResponse

interface SplashService {
    fun checkUser(response: SplashResponse)
    fun isLogged(): Boolean
}
class SplashServices(
    private val repository: SplashRepository
):SplashService {
    override fun checkUser(response: SplashResponse) {
        return repository.checkUser(response)
    }

    override fun isLogged(): Boolean {
        return repository.isLogged()
    }
}