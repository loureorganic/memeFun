package com.example.bookappkotlin.di

import com.example.bookappkotlin.home.utils.ImageLoader
import org.koin.dsl.module

var glideModule = module{

    single {
        val imageLoader = ImageLoader()
        imageLoader
    }
}