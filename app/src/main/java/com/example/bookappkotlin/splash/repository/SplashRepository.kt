package com.example.bookappkotlin.splash.repository

import com.example.bookappkotlin.ApplicationConstants
import com.example.bookappkotlin.helpper.AuthenticationHelper
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import org.koin.core.component.KoinComponent

typealias SplashResponse = (success: Boolean) -> Unit

interface RepositorySplash {
    fun checkUser(response: SplashResponse)
}

class SplashRepository (): RepositorySplash, KoinComponent {

    lateinit var databaseAuthenticationHelper : AuthenticationHelper

    override fun checkUser(response: SplashResponse) {

        databaseAuthenticationHelper = DatabaseAuthenticationHelper()

        val firebaseUser = databaseAuthenticationHelper.databaseAuthentication().currentUser
        if (firebaseUser == null) {
            response(false)
        } else {
            val firebaseUser = databaseAuthenticationHelper.databaseAuthentication().currentUser!!
            val ref = databaseAuthenticationHelper.liveDatabase().getReference(ApplicationConstants.FIREBASE_USERS)
                ref.child(firebaseUser.uid)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val userType = snapshot.child("userType").value
                            if(userType == "user"){
                                response(true)
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }
                    })
        }
    }
}