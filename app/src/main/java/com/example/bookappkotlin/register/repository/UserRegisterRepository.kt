package com.example.bookappkotlin.register.repository

import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.register.model.UserRegister
import io.reactivex.Observable
import org.koin.core.component.KoinComponent

interface RegisterRepository {
    fun createUserAccount(user: UserRegister) : Observable<Boolean>
}

class UserRegisterRepository :RegisterRepository, KoinComponent {

    private val databaseAuthenticationHelper = DatabaseAuthenticationHelper()

    override fun createUserAccount(user: UserRegister): Observable<Boolean> {
      return  databaseAuthenticationHelper.createUserAccount(user = user)
    }
}