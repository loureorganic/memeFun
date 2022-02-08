package com.example.bookappkotlin.app

import android.app.Application
import com.example.bookappkotlin.di.apiModule
import com.example.bookappkotlin.di.repositoryModule
import com.example.bookappkotlin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


//DSL Application where Koin has been initialized
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()

    }

    private fun initKoin() {
        //create a container configuration and register it in the to allow the use of GlobalContext
        startKoin {
            androidContext(this@App)
            // set a list of Koin modules to load in the container (list or vararg list)
            modules(listOf(viewModelModule, repositoryModule, apiModule))
        }
    }
}