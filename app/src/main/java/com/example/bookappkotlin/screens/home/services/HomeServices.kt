package com.example.bookappkotlin.screens.home.services

import com.example.bookappkotlin.screens.home.model.MemeResponse
import com.example.bookappkotlin.screens.home.repository.HomeRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
            .subscribeOn(Schedulers.io())
            .filter { it.data?.meme != null }
            .doOnError { error ->
                error.printStackTrace()
            }
            .observeOn(AndroidSchedulers.mainThread())

    }

    override fun signOutUser() {
       return repository.signOutUser()
    }
}