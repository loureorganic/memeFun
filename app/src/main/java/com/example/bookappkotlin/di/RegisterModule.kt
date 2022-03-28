package com.example.bookappkotlin.di

import com.example.bookappkotlin.screens.register.repository.RegisterRepository
import com.example.bookappkotlin.screens.register.repository.UserRegisterRepository
import com.example.bookappkotlin.screens.register.services.RegisterService
import com.example.bookappkotlin.screens.register.services.UserRegisterServices
import org.koin.dsl.module

val registerModule = module {

   single<RegisterRepository> {
        UserRegisterRepository()
    }

    single<RegisterService> {
        UserRegisterServices()
    }
}