package com.example.bookappkotlin.di.modules

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val firebaseAuthModule = module {

  factory{
         FirebaseAuth.getInstance()
    }
}