package com.example.bookappkotlin.screens.home.services

import com.example.bookappkotlin.repositories.network.api.model.MemeResponse
import com.example.bookappkotlin.screens.home.repository.HomeRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface HomeServices {
    fun getAllMemes(): Observable<MemeResponse?>
    fun signOutUser()
}

class UserHomeServices : HomeServices, KoinComponent {

    private val repository by inject<HomeRepository>()

    override fun getAllMemes(): Observable<MemeResponse?> {

        return repository
            .getAllMemes()
            .filter { it.isSuccessful }
            .map { it.body() }
            .subscribeOn(Schedulers.io())
            .filter { it.data?.meme != null }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable -> "ERROR $throwable" }
    }

    override fun signOutUser() {
        return repository.signOutUser()
    }
}
