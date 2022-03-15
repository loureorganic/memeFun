package com.example.bookappkotlin.di

import com.example.bookappkotlin.register.repository.RegisterRepository
import com.example.bookappkotlin.register.repository.UserRegisterRepository
import com.example.bookappkotlin.register.services.RegisterService
import com.example.bookappkotlin.register.services.UserRegisterServices
import org.koin.dsl.module

val registerRepositoryModule = module {

   single<RegisterRepository> {
        UserRegisterRepository()
    }

    single<RegisterService> {
        UserRegisterServices()
    }
}