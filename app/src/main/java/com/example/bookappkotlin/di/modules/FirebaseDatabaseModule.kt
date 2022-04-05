package com.example.bookappkotlin.di.modules

import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module


val firebaseDatabaseModule = module {

    single{
       FirebaseDatabase.getInstance()
    }
}