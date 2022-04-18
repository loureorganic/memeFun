package com.example.bookappkotlin.screens.login.services

import android.util.Patterns
import com.example.bookappkotlin.screens.login.model.UserLogin
import com.example.bookappkotlin.screens.login.utils.LoginConstants

class ServicesLoginUserTest {

    fun dataValidation(user: UserLogin): String {
        return if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            LoginConstants.INVALID_EMAIL
        } else if (user.password.isEmpty() && user.password.length >= 6) {
            LoginConstants.EMPTY_PASSWORD
        } else {
            LoginConstants.VALID
        }
    }


}