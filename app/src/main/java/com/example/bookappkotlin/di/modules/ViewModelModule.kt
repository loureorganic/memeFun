package com.example.bookappkotlin.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.bookappkotlin.repositories.database.UserViewModel
import com.example.bookappkotlin.repositories.database.ViewModelUser
import com.example.bookappkotlin.screens.home.viewmodel.HomeViewModel
import com.example.bookappkotlin.screens.home.viewmodel.ViewModelHome
import com.example.bookappkotlin.screens.login.viewmodel.LoginViewModel
import com.example.bookappkotlin.screens.login.viewmodel.ViewModelLogin
import com.example.bookappkotlin.screens.profile.viewmodel.ProfileViewModel
import com.example.bookappkotlin.screens.profile.viewmodel.ViewModelProfile
import com.example.bookappkotlin.screens.register.viewmodel.RegisterViewModel
import com.example.bookappkotlin.screens.register.viewmodel.ViewModelRegister
import com.example.bookappkotlin.screens.splash.viewmodel.SplashViewModel
import com.example.bookappkotlin.screens.splash.viewmodel.ViewModelSplash
import org.koin.dsl.module

val viewModelModule = module{

    single<ViewModelProfile>{
        lateinit var profileViewModel: ViewModelProfile
        profileViewModel = ViewModelProvider(get())[ProfileViewModel::class.java]
        profileViewModel
    }

    single<ViewModelLogin>{
        lateinit var loginViewModel: ViewModelLogin
        loginViewModel = ViewModelProvider(get())[LoginViewModel::class.java]
        loginViewModel
    }

    single<ViewModelHome>{
        lateinit var homeViewModel: ViewModelHome
        homeViewModel = ViewModelProvider(get())[HomeViewModel::class.java]
        homeViewModel
    }

    single<ViewModelRegister>{
        lateinit var registerViewModel: ViewModelRegister
        registerViewModel = ViewModelProvider(get())[RegisterViewModel::class.java]
        registerViewModel
    }

    single<ViewModelSplash>{
        lateinit var splashViewModel: ViewModelSplash
        splashViewModel = ViewModelProvider(get())[SplashViewModel::class.java]
        splashViewModel
    }

    single<ViewModelUser>{
        lateinit var userViewModel: ViewModelUser
        userViewModel = ViewModelProvider(get())[UserViewModel::class.java]
        userViewModel
    }


}