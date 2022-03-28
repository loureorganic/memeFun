package com.example.bookappkotlin.screens.splash.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappkotlin.R
import com.example.bookappkotlin.screens.home.ui.HomeActivity
import com.example.bookappkotlin.screens.login.ui.LoginActivity
import com.example.bookappkotlin.screens.splash.services.SplashService
import org.koin.android.ext.android.inject


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val splashService by inject<SplashService>()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            splashService.checkUser().subscribe {
                response -> redirectUserDashboard(accepted = response)
            }
        }, 2000)
    }

    private fun redirectUserDashboard(accepted: Boolean) {
        if (!accepted) {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        } else if (accepted) {
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        }
    }
}