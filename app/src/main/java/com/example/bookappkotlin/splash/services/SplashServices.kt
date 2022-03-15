package com.example.bookappkotlin.splash.services

import com.example.bookappkotlin.splash.repository.RepositorySplash
import com.example.bookappkotlin.splash.repository.SplashResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface SplashService {
    fun checkUser(response: SplashResponse)
}
class SplashServices():SplashService, KoinComponent {

    private val repository by inject<RepositorySplash>()
    override fun checkUser(response: SplashResponse) {
        return repository.checkUser(response)
    }
}