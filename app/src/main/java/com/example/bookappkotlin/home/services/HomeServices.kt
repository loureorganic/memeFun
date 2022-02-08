package com.example.bookappkotlin.home.services

import com.example.bookappkotlin.home.model.MemeResponse
import com.example.bookappkotlin.home.repository.HomeRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

interface HomeServices {
    fun getAllMemes() : Observable<MemeResponse?>
}

class UserHomeServices(
    private val repository: HomeRepository
) : HomeServices {

    override fun getAllMemes() : Observable<MemeResponse?> {
        return repository
            .getAllMemes()
            .filter { it.isSuccessful }
            .map { it.body() }
            .doOnError { it.printStackTrace() }
    }
}