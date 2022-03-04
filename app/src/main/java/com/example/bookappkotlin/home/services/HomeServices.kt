package com.example.bookappkotlin.home.services

import android.util.Log
import com.example.bookappkotlin.home.model.MemeResponse
import com.example.bookappkotlin.home.repository.HomeRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface HomeServices {
    fun getAllMemes() : Observable<MemeResponse?>
}

class UserHomeServices() : HomeServices, KoinComponent {

    private val repository2 by inject<HomeRepository>()

    override fun getAllMemes() : Observable<MemeResponse?> {

        return repository2
            .getAllMemes()
            .filter { it.isSuccessful }
            .map { it.body() }
            .doOnError { it.printStackTrace() }
    }
}