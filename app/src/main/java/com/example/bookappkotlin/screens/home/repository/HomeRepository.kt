package com.example.bookappkotlin.screens.home.repository

import com.example.bookappkotlin.repositories.helpper.AuthenticationHelper
import com.example.bookappkotlin.screens.home.model.MemeResponse
import com.example.bookappkotlin.repositories.network.api.MemeApi
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

interface HomeRepository {
    fun getAllMemes(): Observable<Response<MemeResponse>>
    fun signOutUser()
}

class UserHomeRepository : HomeRepository, KoinComponent {

    private val apiCall by inject<MemeApi>()
    private val databaseAuthentication by inject<AuthenticationHelper>()
    private lateinit var databaseAuthenticationHelper: AuthenticationHelper

    override fun getAllMemes(): Observable<Response<MemeResponse>> {
        return apiCall.getData()
    }

    override fun signOutUser() {
        databaseAuthenticationHelper = databaseAuthentication
        databaseAuthenticationHelper.signOutUser()
    }
}