package com.example.bookappkotlin.repositories.network.api

import com.example.bookappkotlin.repositories.network.api.model.MemeResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface MemeApi {
    @GET("/get_memes")
    fun getData(): Observable<Response<MemeResponse>>
}