package com.example.bookappkotlin.home.network.client

import com.example.bookappkotlin.home.model.MemeResponse
import com.example.bookappkotlin.home.network.api.MemeApi
import io.reactivex.Observable
import retrofit2.Response

open class MemeClient(private val api: MemeApi) {

    fun getAllMemes() : Observable<Response<MemeResponse>>{
        return api.getData()
    }

}