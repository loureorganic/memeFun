package com.example.bookappkotlin.screens.splash.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappkotlin.databinding.ActivitySplashBinding
import com.example.bookappkotlin.screens.home.ui.HomeActivity
import com.example.bookappkotlin.screens.login.ui.LoginActivity
import com.example.bookappkotlin.screens.splash.viewmodel.SplashViewModel
import com.example.bookappkotlin.screens.splash.viewmodel.ViewModelSplash
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val viewModelSplash by inject<ViewModelSplash>() {
        parametersOf(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        Handler().postDelayed({

            viewModelSplash.checkUser()

            viewModelSplash.errorCheckUserLiveData.observe(this) { error ->
                if (error) {
                    Toast.makeText(
                        this,
                        "Error on trying to checkUser, try later soon!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    (viewModelSplash as SplashViewModel).booleanCheckUserLiveData.observe(this) { result ->
                        redirectUserDashboard(result)
                    }
                }
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