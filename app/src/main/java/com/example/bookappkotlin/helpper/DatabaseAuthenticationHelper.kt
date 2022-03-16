package com.example.bookappkotlin.helpper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface AuthenticationHelper {
    fun databaseAuthentication(): FirebaseAuth
    fun liveDatabase() : FirebaseDatabase
}

class DatabaseAuthenticationHelper : AuthenticationHelper, KoinComponent{

    private val firebaseAuth by inject<FirebaseAuth>()
    private val firebaseDatabase by inject<FirebaseDatabase>()

    override fun databaseAuthentication(): FirebaseAuth {
        return firebaseAuth
    }

    override fun liveDatabase() : FirebaseDatabase {
        return firebaseDatabase
    }

}