package com.example.bookappkotlin.di

import com.example.bookappkotlin.splash.repository.RepositorySplash
import com.example.bookappkotlin.splash.repository.SplashRepository
import com.example.bookappkotlin.splash.services.SplashService
import com.example.bookappkotlin.splash.services.SplashServices
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module


val splashRepositoryModule = module {

    single<RepositorySplash>{
        val firebase = FirebaseAuth.getInstance()
        SplashRepository(firebaseAuth = firebase, preferences = get())
    }

    factory<SplashService>{
        SplashServices(get())
    }

}