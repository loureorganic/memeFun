package com.example.bookappkotlin.screens.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookappkotlin.screens.login.model.UserLogin
import com.example.bookappkotlin.screens.login.services.LoginService
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test


class LoginViewModelTest {

    private lateinit var viewModel: ViewModelLogin

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun viewModelTestLoginUser(){

        val user = UserLogin(
            email = "kaiqueguimaraes@gmail.com",
            password = "123456"
        )

        val userServices : LoginService =mockk()

        every{userServices.loginUser(any())}returns Observable.create{it ->
            it.onNext(true)
        }

        viewModel = LoginViewModel(services = userServices)

        viewModel.loginUser(user)

        viewModel.booleanLoginAccountLiveData.observeForever{
                result->
            assert(result == true)
        }
    }

    @Test
    fun viewModelTestDataValidation(){

        val user = UserLogin(
            email = "kaiqueguimaraes@gmail.com",
            password = "123456"
        )
        val userServices : LoginService =mockk()

        every{userServices.dataValidation(any())} returns "VALID"

        viewModel = LoginViewModel(services = userServices)
        val result  = viewModel.dataValidation(user)
        assert(result == "VALID")
    }

}