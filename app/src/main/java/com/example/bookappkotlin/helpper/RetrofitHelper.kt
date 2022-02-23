package com.example.bookappkotlin.helpper

import com.example.bookappkotlin.home.model.MemeResponse
import com.example.bookappkotlin.home.network.api.MemeApi
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
        fun initRetrofit(BASE_URL: String): Observable<Response<MemeResponse>> {

            val gson = GsonBuilder()
                .create()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient().newBuilder().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(MemeApi::class.java)
                .getData()
    }
}