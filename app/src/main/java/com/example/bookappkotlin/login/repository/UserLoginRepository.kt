package com.example.bookappkotlin.login.repository

import android.content.SharedPreferences
import com.example.bookappkotlin.login.model.User
import com.google.firebase.auth.FirebaseAuth

typealias LoginResponse = (success: Boolean) -> Unit

interface LoginRepository {
    fun loginUser(user: User, response: LoginResponse)
    fun isLogged(): Boolean
}

class UserLoginRepository(
   private val firebaseAuth: FirebaseAuth,
   private val preferences: SharedPreferences,
): LoginRepository {

    private val loginKey = "login"

    override fun loginUser(user: User, response: LoginResponse) {
        firebaseAuth.signInWithEmailAndPassword(user.email,user.password)
            .addOnSuccessListener {
                preferences.edit().putBoolean(loginKey, true).apply()
                response(true)
            }
            .addOnFailureListener{
                response(false)
            }
    }

    override fun isLogged(): Boolean {
        return preferences.getBoolean(loginKey, false)
    }
}