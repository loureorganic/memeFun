package com.example.bookappkotlin.home.repository

import com.example.bookappkotlin.home.model.MemeResponse
import com.example.bookappkotlin.network.api.MemeApi
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

interface HomeRepository {
    fun getAllMemes(): Observable<Response<MemeResponse>>
}

class UserHomeRepository : HomeRepository, KoinComponent {

    private val apiCall by inject<MemeApi>()

    override fun getAllMemes(): Observable<Response<MemeResponse>> {
        return apiCall.getData()
    }
}