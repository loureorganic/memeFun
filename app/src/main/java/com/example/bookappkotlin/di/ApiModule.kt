package com.example.bookappkotlin.di

import com.example.bookappkotlin.helpper.RetrofitHelpper
import com.example.bookappkotlin.home.network.api.MemeApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

const val BASE_DEFAULT = "BASE_DEFAULT"

val apiModule = module {
    //initialization of retrofit helpper
   single {
       RetrofitHelpper.initRetrofit()
   }
    //?????
    single {
        (get<Retrofit>(named(BASE_DEFAULT))).create(MemeApi::class.java)
    }
}