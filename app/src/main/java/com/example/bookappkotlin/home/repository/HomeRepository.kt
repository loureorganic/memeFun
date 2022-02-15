package com.example.bookappkotlin.home.repository

import android.util.Log
import com.example.bookappkotlin.helpper.RetrofitHelper
import com.example.bookappkotlin.home.model.MemeResponse
import com.example.bookappkotlin.home.network.client.MemeClient
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

interface HomeRepository {
    fun getAllMemes(): Observable<Response<MemeResponse>>
}

class UserHomeRepository() : HomeRepository, KoinComponent {

    private val baseUrl = "https://api.imgflip.com"
    private val apiCall by inject<RetrofitHelper>()

    override fun getAllMemes(): Observable<Response<MemeResponse>> {
        return apiCall.initRetrofit(BASE_URL = baseUrl)
    }
}