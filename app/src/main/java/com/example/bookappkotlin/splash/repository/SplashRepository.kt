package com.example.bookappkotlin.splash.repository

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

typealias SplashResponse = (success: Boolean) -> Unit

interface RepositorySplash {
    fun checkUser(response: SplashResponse)
    fun isLogged(): Boolean
}

class SplashRepository (
    private val firebaseAuth: FirebaseAuth,
    private val preferences: SharedPreferences,
): RepositorySplash {

    private val splashKey = "splash"
    override fun checkUser(response: SplashResponse) {

        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser == null) {
            preferences.edit().putBoolean(splashKey, false).apply()
            response(false)
        } else {
            val firebaseUser = firebaseAuth.currentUser!!

            val ref = FirebaseDatabase.getInstance().getReference("Users")

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