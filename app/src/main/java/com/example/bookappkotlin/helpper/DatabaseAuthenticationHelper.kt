package com.example.bookappkotlin.helpper

import com.google.firebase.auth.FirebaseAuth
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface HelperFirebase{
    fun databaseAuthentication(): FirebaseAuth
}


class DatabaseAuthenticationHelper : HelperFirebase, KoinComponent{

    private val firebaseAuth by inject<FirebaseAuth>()

    override fun databaseAuthentication(): FirebaseAuth {
        return firebaseAuth
    }

}