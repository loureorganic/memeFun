package com.example.bookappkotlin.di


import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val firebaseModule = module {

    factory {
        FirebaseAuth.getInstance()
    }
}