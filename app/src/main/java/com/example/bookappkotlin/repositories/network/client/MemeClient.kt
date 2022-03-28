package com.example.bookappkotlin.repositories.network.client

import com.example.bookappkotlin.screens.home.model.MemeResponse
import com.example.bookappkotlin.repositories.network.api.MemeApi
import io.reactivex.Observable
import retrofit2.Response

open class MemeClient(private val api: MemeApi) {

    fun getAllMemes() : Observable<Response<MemeResponse>>{
        return api.getData()
    }

}