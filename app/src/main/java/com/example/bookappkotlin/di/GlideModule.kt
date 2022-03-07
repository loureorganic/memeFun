package com.example.bookappkotlin.di

import com.example.bookappkotlin.home.utils.GlideImageLoader
import org.koin.dsl.module

var glideModule = module{

    factory {
        val glideImage = GlideImageLoader()
        glideImage
    }
}