package com.example.bookappkotlin.login.repository

import com.example.bookappkotlin.helpper.AuthenticationHelper
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.login.model.UserLogin
import org.koin.core.component.KoinComponent

typealias LoginResponse = (success: Boolean) -> Unit

interface LoginRepository {
    fun loginUser(userLogin: UserLogin, response: LoginResponse)
}

class UserLoginRepository(
): LoginRepository, KoinComponent {

    lateinit var databaseAuthenticationHelper: AuthenticationHelper

    override fun loginUser(userLogin: UserLogin, response: LoginResponse) {

        databaseAuthenticationHelper = DatabaseAuthenticationHelper()

        databaseAuthenticationHelper.databaseAuthentication().signInWithEmailAndPassword(userLogin.email,userLogin.password)
            .addOnSuccessListener {
                response(true)
            }
            .addOnFailureListener{
                response(false)
            }
    }

}