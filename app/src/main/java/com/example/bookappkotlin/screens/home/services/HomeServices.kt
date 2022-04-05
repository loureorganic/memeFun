package com.example.bookappkotlin.screens.home.services

import android.util.Log
import com.example.bookappkotlin.screens.home.model.MemeResponse
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

        Log.i("TEST", "O QUE VEM DE REPOSITORY" + repository.getAllMemes())

       val value =  repository
            .getAllMemes()
            .filter { it.isSuccessful }
            .map { it.body() }
            .subscribeOn(Schedulers.io())
            .filter { it.data?.meme != null }
            .observeOn(AndroidSchedulers.mainThread())
           .doOnError { throwable -> "ERROR $throwable"  }
                return value

        /*return repository
            .getAllMemes()
            .filter { it.isSuccessful }
            .map { it.body() }
            .subscribeOn(Schedulers.io())
            .filter { it.data?.meme != null }
            .observeOn(AndroidSchedulers.mainThread())*/
    }

    override fun signOutUser() {
        return repository.signOutUser()
    }
}
