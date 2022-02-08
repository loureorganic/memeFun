package com.example.bookappkotlin.di

import com.example.bookappkotlin.ApiInterface
import com.example.bookappkotlin.helpper.RetrofitHelpper
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

const val BASE_DEFAULT = "BASE_DEFAULT"

val apiModule = module {
    //initialization of retrofit helpper
   single {
       RetrofitHelpper.initRetrofit()
   }
    //?????
    single {
        (get<Retrofit>(named(BASE_DEFAULT))).create(ApiInterface::class.java)
    }
}