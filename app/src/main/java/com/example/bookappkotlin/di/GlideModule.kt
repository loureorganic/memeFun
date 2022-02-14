package com.example.bookappkotlin.di

import com.example.bookappkotlin.home.GlideImageLoader
import org.koin.dsl.module

var glideModule = module{

    factory {
        GlideImageLoader()
    }
}