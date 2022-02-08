package com.example.bookappkotlin.di

import com.example.bookappkotlin.dashboard.repository.DashboardRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        DashboardRepository(get())
    }
}