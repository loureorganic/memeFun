package com.example.bookappkotlin.di

import com.example.bookappkotlin.screens.login.repository.LoginRepository
import com.example.bookappkotlin.screens.login.repository.UserLoginRepository
import com.example.bookappkotlin.screens.login.services.LoginService
import com.example.bookappkotlin.screens.login.services.UserLoginServices
import org.koin.dsl.module


val loginModule = module {
    single<LoginRepository>{
        UserLoginRepository()
    }

    single<LoginService>{
        UserLoginServices()
    }
}