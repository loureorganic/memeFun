package com.example.bookappkotlin.screens.home.services

import com.example.bookappkotlin.screens.home.model.MemeResponse
import com.example.bookappkotlin.screens.home.repository.HomeRepository
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface HomeServices {
    fun getAllMemes() : Observable<MemeResponse?>
    fun signOutUser()
}

class UserHomeServices : HomeServices, KoinComponent {

    private val repository by inject<HomeRepository>()

    override fun getAllMemes() : Observable<MemeResponse?> {

        return repository
            .getAllMemes()
            .filter { it.isSuccessful }
            .map { it.body() }
            .doOnError { it.printStackTrace() }
    }

    override fun signOutUser() {
       return repository.signOutUser()
    }
}