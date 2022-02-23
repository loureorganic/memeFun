package com.example.bookappkotlin.di

import android.util.Log
import com.example.bookappkotlin.helpper.RetrofitHelper
import com.example.bookappkotlin.home.network.api.MemeApi
import com.example.bookappkotlin.home.network.client.MemeClient
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_DEFAULT = "BASE_DEFAULT"

val apiModule = module {

    val baseUrl = "https://api.imgflip.com"

    factory {

        val gson = GsonBuilder()
            .create()

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient().newBuilder().build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    factory<MemeApi> {
        val retrofit: Retrofit = get()
        Log.i("DATA", "DATA $retrofit")
        retrofit.create(MemeApi::class.java)

    }
}
