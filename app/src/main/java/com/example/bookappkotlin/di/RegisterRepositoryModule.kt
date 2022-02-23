package com.example.bookappkotlin.di

import com.example.bookappkotlin.register.repository.RegisterRepository
import com.example.bookappkotlin.register.repository.UserRegisterRepository
import com.example.bookappkotlin.register.services.RegisterService
import com.example.bookappkotlin.register.services.UserRegisterServices
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module


val registerRepositoryModule = module {

   single<RegisterRepository> {
        val firebase = FirebaseAuth.getInstance()
        UserRegisterRepository(firebaseAuth = firebase, preferences = get())
    }

    factory<RegisterService> {
        UserRegisterServices(repository = get())
    }
}