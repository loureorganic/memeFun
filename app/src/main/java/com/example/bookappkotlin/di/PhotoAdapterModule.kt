package com.example.bookappkotlin.di

import com.example.bookappkotlin.screens.home.adapters.PhotoAdapter
import org.koin.dsl.module

val photoAdapterModule = module {

    single {
        PhotoAdapter(get())
    }
}