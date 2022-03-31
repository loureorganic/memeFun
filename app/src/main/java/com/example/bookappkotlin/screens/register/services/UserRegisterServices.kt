package com.example.bookappkotlin.screens.register.services

import android.util.Patterns
import com.example.bookappkotlin.screens.register.model.UserRegister
import com.example.bookappkotlin.screens.register.repository.RegisterRepository
import com.example.bookappkotlin.screens.register.utils.RegisterConstants
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RegisterService {
    fun dataValidation(user: UserRegister): String
    fun createUserAccount(user: UserRegister): Observable<Boolean>
}

class UserRegisterServices() : RegisterService, KoinComponent {

    private val repository by inject<RegisterRepository>()

     override fun dataValidation(user: UserRegister): String {
         return if (user.name.isEmpty()) {
             RegisterConstants.NAME_EMPTY
         } else if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
             RegisterConstants.EMAIL_INVALID
         } else if (user.password.isEmpty()) {
             RegisterConstants.PASSWORD_EMPTY
         } else if (user.confirmPassword.isEmpty()) {
             RegisterConstants.CONFIRM_PASSWORD_EMPTY
         } else if (user.password != user.confirmPassword) {
             RegisterConstants.PASSWORD_NOT_MATCH
         } else {
             RegisterConstants.VALID
         }
    }

    override fun createUserAccount(user: UserRegister): Observable<Boolean> {
        return repository.createUserAccount(user)
    }
}