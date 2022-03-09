package com.example.bookappkotlin.splash.repository

import android.content.SharedPreferences
import com.example.bookappkotlin.ApplicationConstants
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.helpper.DatabaseGeneralHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import org.koin.core.component.KoinComponent

typealias SplashResponse = (success: Boolean) -> Unit

interface RepositorySplash {
    fun checkUser(response: SplashResponse)
    fun isLogged(): Boolean
}

class SplashRepository (
    private val preferences: SharedPreferences,
): RepositorySplash, KoinComponent {

    private val splashKey = "splash"

    override fun checkUser(response: SplashResponse) {

        val databaseAuthenticationHelper = DatabaseAuthenticationHelper()
        val databaseGeneralHelper = DatabaseGeneralHelper()
        val applicationConstants = ApplicationConstants()

        val firebaseUser = databaseAuthenticationHelper.databaseAuthentication().currentUser
        if (firebaseUser == null) {
            preferences.edit().putBoolean(splashKey, false).apply()
            response(false)
        } else {
            val ref = databaseGeneralHelper.liveDatabase().getReference(applicationConstants.FIREBASE_USERS)
                ref.child(firebaseUser.uid)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val userType = snapshot.child("userType").value
                            if(userType == "user"){
                                preferences.edit().putBoolean(splashKey, true).apply()
                                response(true)
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }
                    })
        }
    }

    override fun isLogged(): Boolean {
        return preferences.getBoolean(splashKey, false)
    }
}