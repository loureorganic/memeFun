package com.example.bookappkotlin.screens.login.services

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

class LoginServicesTest {

    private lateinit var  services : LoginService

    @get:Rule
    val rule = InstantTaskExecutorRule()

   /* @Test
    fun servicesDataValidation(){
        val user = UserLogin(
            email = "kaiqueguimaraes@gmail.com",
            password = "123456"
        )
        val repository : LoginRepository = mockk()

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


    }*/


}