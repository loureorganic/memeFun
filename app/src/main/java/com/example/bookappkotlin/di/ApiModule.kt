package com.example.bookappkotlin.di

import com.example.bookappkotlin.repositories.helpper.RetrofitHelper
import com.example.bookappkotlin.repositories.network.api.MemeApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    val baseUrl = "https://api.imgflip.com"

    factory {
        val retrofitHelper = RetrofitHelper()
        retrofitHelper.initRetrofit(baseUrl)
    }

    factory<MemeApi> {
        val retrofit: Retrofit = get()
        retrofit.create(MemeApi::class.java)
    }
}
