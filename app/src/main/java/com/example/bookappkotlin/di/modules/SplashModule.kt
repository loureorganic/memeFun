package com.example.bookappkotlin.di.modules

import com.example.bookappkotlin.screens.splash.repository.RepositorySplash
import com.example.bookappkotlin.screens.splash.repository.SplashRepository
import com.example.bookappkotlin.screens.splash.services.SplashService
import com.example.bookappkotlin.screens.splash.services.SplashServices
import org.koin.dsl.module


val splashModule = module {
    single<RepositorySplash>{
        SplashRepository()
    }

    single<SplashService>{
        SplashServices()
    }
}