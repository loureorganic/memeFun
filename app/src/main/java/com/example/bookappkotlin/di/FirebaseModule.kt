package com.example.bookappkotlin.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val firebaseModule = module {

    factory<FirebaseAuth> {
       FirebaseAuth.getInstance()
    }
}