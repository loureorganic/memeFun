package com.example.bookappkotlin.di

import com.example.bookappkotlin.repositories.network.api.MemeApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun configureNetworkModuleForTest(baseApi: String) = module {

    val baseUrl = "https://api.imgflip.com"
    val gson = GsonBuilder()
        .create()

    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient().newBuilder().build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    factory<MemeApi> {
        val retrofit: Retrofit = get()
        retrofit.create(MemeApi::class.java)
    }
}