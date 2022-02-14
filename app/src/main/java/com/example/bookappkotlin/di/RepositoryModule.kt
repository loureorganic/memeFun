package com.example.bookappkotlin.di

import com.example.bookappkotlin.home.repository.HomeRepository
import com.example.bookappkotlin.home.repository.UserHomeRepository
import com.example.bookappkotlin.home.services.HomeServices
import com.example.bookappkotlin.home.services.UserHomeServices
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val repositoryModule = module {

    factory<HomeRepository>{
        UserHomeRepository()
    }

    factory<HomeServices>{
        UserHomeServices(
            repository = get()
        )
    }
}