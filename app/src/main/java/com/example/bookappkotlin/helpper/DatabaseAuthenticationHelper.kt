package com.example.bookappkotlin.helpper

import com.google.firebase.auth.FirebaseAuth
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface AuthenticationHelper {
    fun databaseAuthentication(): FirebaseAuth
}

class DatabaseAuthenticationHelper : AuthenticationHelper, KoinComponent{

    private val firebaseAuth by inject<FirebaseAuth>()

    override fun databaseAuthentication(): FirebaseAuth {
        return firebaseAuth
    }

}