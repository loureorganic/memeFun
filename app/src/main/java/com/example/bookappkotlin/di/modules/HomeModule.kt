package com.example.bookappkotlin.di.modules

import com.example.bookappkotlin.screens.home.repository.HomeRepository
import com.example.bookappkotlin.screens.home.repository.UserHomeRepository
import com.example.bookappkotlin.screens.home.services.HomeServices
import com.example.bookappkotlin.screens.home.services.UserHomeServices
import org.koin.dsl.module

val homeModule = module {

    factory<HomeRepository>{
        UserHomeRepository()
    }

    single<HomeServices>{
        UserHomeServices()
    }
}