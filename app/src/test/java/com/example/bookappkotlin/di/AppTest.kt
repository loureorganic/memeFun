package com.example.bookappkotlin.di

import android.app.Application
import com.example.bookappkotlin.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class AppTest : Application() {

   override fun onCreate() {
        super.onCreate()
        initKoin()
    }


    private fun initKoin() {

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AppTest)
            androidFileProperties()

            modules(emptyList())
        }

    }

    internal fun injectModule(module: Module){
        loadKoinModules(module)
    }

}