package com.example.bookappkotlin.home.services

import android.util.Log
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

        Log.i("Returned", "Return value service inside " + repository.getAllMemes())
        //retrofit2.adapter.rxjava2.CallExecuteObservable@38a748e

        return repository
            .getAllMemes()
            .filter { it.isSuccessful }
            .map { it.body() }
            .doOnError { it.printStackTrace() }
    }
}