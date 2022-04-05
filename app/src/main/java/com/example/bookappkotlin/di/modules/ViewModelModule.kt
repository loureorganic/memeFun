package com.example.bookappkotlin.di.modules

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
        ProfileViewModel()
    }

    single<ViewModelLogin>{
        LoginViewModel()
    }

    single<ViewModelHome>{
        HomeViewModel()
    }

    single<ViewModelRegister>{
        RegisterViewModel()
    }

    single<ViewModelSplash>{
        SplashViewModel()
    }
}