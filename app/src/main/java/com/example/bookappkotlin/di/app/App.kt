package com.example.bookappkotlin.di.app

import android.app.Application
import com.example.bookappkotlin.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            androidFileProperties()

            modules(
                listOf(
                    homeModule,
                    apiModule,
                    registerModule,
                    loginModule,
                    splashModule,
                    glideModule,
                    photoAdapterModule,
                    firebaseAuthModule,
                    firebaseDatabaseModule,
                    profileModule,
                    databaseAuthenticationModule,
                    viewModelModule
                )
            )
        }
    }
}