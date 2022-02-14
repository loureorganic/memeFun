package com.example.bookappkotlin.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappkotlin.home.HomeActivity
import com.example.bookappkotlin.R
import com.example.bookappkotlin.login.controllers.LoginActivity
import com.example.bookappkotlin.register.repository.RegisterRepository
import com.example.bookappkotlin.register.services.RegisterService
import com.example.bookappkotlin.splash.repository.RepositorySplash
import com.example.bookappkotlin.splash.repository.SplashRepository
import com.example.bookappkotlin.splash.services.SplashService
import com.example.bookappkotlin.splash.services.SplashServices
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : AppCompatActivity() {

    private val splashRepository by inject<RepositorySplash>(){
        parametersOf(getPreferences(MODE_PRIVATE))
    }

    private val splashService by inject<SplashService>(){
        parametersOf(splashRepository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            splashService.checkUser(this::redirectUserDashboard)
        }, 2000)
    }


    private fun redirectUserDashboard(accepted: Boolean) {
        if (!accepted) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else if (accepted) {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }

}