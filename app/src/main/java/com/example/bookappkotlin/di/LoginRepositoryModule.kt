package com.example.bookappkotlin.di

import com.example.bookappkotlin.login.repository.LoginRepository
import com.example.bookappkotlin.login.repository.UserLoginRepository
import com.example.bookappkotlin.login.services.LoginService
import com.example.bookappkotlin.login.services.UserLoginServices
import org.koin.dsl.module


val loginRepositoryModule = module {

    single<LoginRepository>{
        UserLoginRepository(preferences = get())
    }

    factory<LoginService>{
        UserLoginServices(get())
    }
}