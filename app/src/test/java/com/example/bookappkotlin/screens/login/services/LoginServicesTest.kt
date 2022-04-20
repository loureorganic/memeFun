package com.example.bookappkotlin.screens.login.services

import android.annotation.SuppressLint
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookappkotlin.screens.login.model.UserLogin
import com.example.bookappkotlin.screens.login.repository.LoginRepository
import com.example.bookappkotlin.screens.login.utils.LoginConstants
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test

class LoginServicesTest {

    private lateinit var  services : LoginService

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun servicesDataValidation(){
        val user = UserLogin(
            email = "kaiqueguimaraes@gmail.com",
            password = "123456"
        )
        val repository : LoginRepository = mockk()
        services = UserLoginServices(repository)

        val result = services.dataValidation(user)
        assert(result === LoginConstants.VALID)
    }

    @SuppressLint("CheckResult")
    @Test
    fun servicesLoginUser(){
        val user = UserLogin(
            email = "kaiqueguimaraes@gmail.com",
            password = "123456"
        )

        val repository : LoginRepository = mockk()

        every { repository.loginUser(any()) } returns Observable.create {
            it.onNext(true)
        }

        services = UserLoginServices(repository =  repository)

       val result = services.loginUser(user)

        result.subscribe { it ->
            assert(it == true)
        }


    }


}