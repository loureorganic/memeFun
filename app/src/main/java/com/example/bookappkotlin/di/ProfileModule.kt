package com.example.bookappkotlin.di

import com.example.bookappkotlin.profile.repository.ProfileRepository
import com.example.bookappkotlin.profile.repository.RepositoryProfile
import com.example.bookappkotlin.profile.services.ProfileServices
import com.example.bookappkotlin.profile.services.ServiceProfile
import org.koin.dsl.module

val profileModule = module{
    single<RepositoryProfile>{
      ProfileRepository()
    }

    single<ServiceProfile>{
       ProfileServices()
    }
}