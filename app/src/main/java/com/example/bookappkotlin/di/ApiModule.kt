package com.example.bookappkotlin.di

import com.example.bookappkotlin.helpper.RetrofitHelper
import com.example.bookappkotlin.home.network.api.MemeApi
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_DEFAULT = "BASE_DEFAULT"

val apiModule = module {

    single{
       RetrofitHelper()
    }

}
