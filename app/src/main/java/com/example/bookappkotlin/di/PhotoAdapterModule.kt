package com.example.bookappkotlin.di

import com.example.bookappkotlin.home.PhotoAdapter
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val photoAdapterModule = module {

    single {
        PhotoAdapter(get())
    }
}