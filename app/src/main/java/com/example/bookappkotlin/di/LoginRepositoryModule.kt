package com.example.bookappkotlin.di

import com.example.bookappkotlin.login.repository.LoginRepository
import com.example.bookappkotlin.login.repository.UserLoginRepository
import com.example.bookappkotlin.login.services.LoginService
import com.example.bookappkotlin.login.services.UserLoginServices
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module


val loginRepositoryModule = module {

    single<LoginRepository>{
        val firebase = FirebaseAuth.getInstance()
        UserLoginRepository(firebaseAuth = firebase, preferences = get())
    }

    factory<LoginService>{
        UserLoginServices(get())
    }


}