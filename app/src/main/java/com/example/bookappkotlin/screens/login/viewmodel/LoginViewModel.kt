package com.example.bookappkotlin.screens.login.viewmodel

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.bookappkotlin.screens.login.model.UserLogin

class LoginViewModel : ViewModel(){




    @SuppressLint("CheckResult")
    fun validateData(
        user: UserLogin
    ): String {
        //Use constants instead use strings
        if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            return     "INVALID_EMAIL"
        } else if (user.password.isEmpty() && user.password.length >= 6) {
            return    "EMPTY_PASSWORD"
        }  else{
            return "TRUE"
        }
    }
}