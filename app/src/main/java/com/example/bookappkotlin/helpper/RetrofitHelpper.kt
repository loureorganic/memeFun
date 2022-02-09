package com.example.bookappkotlin.helpper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelpper {


    companion object {
        fun initRetrofit(): Retrofit {
            val BASE_URL = "https://api.imgflip.com"
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }
    }

}