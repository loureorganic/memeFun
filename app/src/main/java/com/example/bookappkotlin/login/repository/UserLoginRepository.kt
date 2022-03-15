package com.example.bookappkotlin.login.repository

import com.example.bookappkotlin.helpper.AuthenticationHelper
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.login.model.User
import org.koin.core.component.KoinComponent

typealias LoginResponse = (success: Boolean) -> Unit

interface LoginRepository {
    fun loginUser(user: User, response: LoginResponse)
}

class UserLoginRepository(
): LoginRepository, KoinComponent {

    lateinit var databaseAuthenticationHelper: AuthenticationHelper

    override fun loginUser(user: User, response: LoginResponse) {

        databaseAuthenticationHelper = DatabaseAuthenticationHelper()

        databaseAuthenticationHelper.databaseAuthentication().signInWithEmailAndPassword(user.email,user.password)
            .addOnSuccessListener {
                response(true)
            }
            .addOnFailureListener{
                response(false)
            }
    }

}