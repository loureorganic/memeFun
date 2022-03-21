package com.example.bookappkotlin.splash.services

import com.example.bookappkotlin.splash.repository.RepositorySplash
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface SplashService {
    fun checkUser(): Observable<Boolean>
}
class SplashServices:SplashService, KoinComponent {

    private val repository by inject<RepositorySplash>()

    override fun checkUser() : Observable<Boolean>{
        return repository.checkUser()
    }
}