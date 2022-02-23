package com.example.bookappkotlin.di

import com.example.bookappkotlin.home.utils.PhotoAdapter
import org.koin.dsl.module

val photoAdapterModule = module {

    single {
        PhotoAdapter(get())
    }
}