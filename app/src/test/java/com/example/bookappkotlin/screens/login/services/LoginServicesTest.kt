package com.example.bookappkotlin.screens.login.services

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookappkotlin.screens.login.model.UserLogin
import com.example.bookappkotlin.screens.login.utils.LoginConstants
import com.example.bookappkotlin.screens.login.viewmodel.LoginViewModel
import com.example.bookappkotlin.screens.login.viewmodel.ViewModelLogin
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginServicesTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        startKoin {
            modules(module {
                single<LoginService>{
                    UserLoginServices()
                }
            })
        }
    }

    @After
    fun after(){
        stopKoin()
    }

    private lateinit var services : LoginService


    @Test
    fun testServices(){

        val user = UserLogin(
            email = "kaiqueguimaraes@gmail.com",
            password = "123456"
        )

        services = UserLoginServices()

        val response = services.dataValidation(user)
        assert(response === LoginConstants.VALID)
    }
}