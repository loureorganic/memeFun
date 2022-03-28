package com.example.bookappkotlin.di

import com.example.bookappkotlin.screens.home.adapters.ImageLoader
import org.koin.dsl.module

var glideModule = module{

    single {
        val imageLoader = ImageLoader()
        imageLoader
    }
}