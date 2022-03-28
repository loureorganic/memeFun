package com.example.bookappkotlin.di

import com.example.bookappkotlin.screens.profile.repository.ProfileRepository
import com.example.bookappkotlin.screens.profile.repository.RepositoryProfile
import com.example.bookappkotlin.screens.profile.services.ProfileServices
import com.example.bookappkotlin.screens.profile.services.ServiceProfile
import org.koin.dsl.module

val profileModule = module{
    single<RepositoryProfile>{
      ProfileRepository()
    }

    single<ServiceProfile>{
       ProfileServices()
    }
}