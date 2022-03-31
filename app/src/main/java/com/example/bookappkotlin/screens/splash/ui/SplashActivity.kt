package com.example.bookappkotlin.screens.splash.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bookappkotlin.databinding.ActivitySplashBinding
import com.example.bookappkotlin.screens.home.ui.HomeActivity
import com.example.bookappkotlin.screens.login.ui.LoginActivity
import com.example.bookappkotlin.screens.splash.services.SplashService
import com.example.bookappkotlin.screens.splash.viewmodel.SplashViewModel
import org.koin.android.ext.android.inject


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private lateinit var viewModelSplash: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        viewModelSplash = ViewModelProvider(this)[SplashViewModel::class.java]

        Handler().postDelayed({

            viewModelSplash.checkUser()

            viewModelSplash.checkUserLiveData.observe(this) { result ->
                Log.i("RESULTADO", "HELP $result")
                redirectUserDashboard(result)
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