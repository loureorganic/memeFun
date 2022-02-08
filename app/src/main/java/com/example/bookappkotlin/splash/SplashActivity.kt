package com.example.bookappkotlin.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappkotlin.home.HomeActivity
import com.example.bookappkotlin.R
import com.example.bookappkotlin.login.controllers.LoginActivity
import com.example.bookappkotlin.splash.repository.SplashRepository
import com.example.bookappkotlin.splash.services.SplashServices
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var splashService: SplashServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        setup()

        Handler().postDelayed(Runnable {
            splashService.checkUser(this::redirectUserDashboard)
        }, 2000)
    }

    private fun setup() {
        val preferences = getPreferences(Context.MODE_PRIVATE)
        val firebase = FirebaseAuth.getInstance()
        val repository = SplashRepository(firebase, preferences)
        splashService = SplashServices(repository)
    }

    private fun redirectUserDashboard(accepted: Boolean) {
        if (!accepted) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else if (accepted) {
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        }
    }

}