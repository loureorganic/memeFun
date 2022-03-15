package com.example.bookappkotlin.login.repository

import android.content.SharedPreferences
import com.example.bookappkotlin.helpper.AuthenticationHelper
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.login.model.User
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

typealias LoginResponse = (success: Boolean) -> Unit

interface LoginRepository {
    fun loginUser(user: User, response: LoginResponse)
    fun isLogged(): Boolean
}

class UserLoginRepository(
   private val preferences: SharedPreferences,
): LoginRepository, KoinComponent {

    lateinit var databaseAuthenticationHelper: AuthenticationHelper
    private val loginKey = "login"

    override fun loginUser(user: User, response: LoginResponse) {
        databaseAuthenticationHelper = DatabaseAuthenticationHelper()
        databaseAuthenticationHelper.databaseAuthentication().signInWithEmailAndPassword(user.email,user.password)
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