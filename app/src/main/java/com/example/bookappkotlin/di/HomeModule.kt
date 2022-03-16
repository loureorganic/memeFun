package com.example.bookappkotlin.di

import com.example.bookappkotlin.home.repository.HomeRepository
import com.example.bookappkotlin.home.repository.UserHomeRepository
import com.example.bookappkotlin.home.services.HomeServices
import com.example.bookappkotlin.home.services.UserHomeServices
import org.koin.dsl.module

val homeModule = module {

    factory<HomeRepository>{
        UserHomeRepository()
    }

    single<HomeServices>{
        UserHomeServices()
    }
}