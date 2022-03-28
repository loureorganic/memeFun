package com.example.bookappkotlin.screens.profile.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappkotlin.databinding.ActivityProfileBinding
import com.example.bookappkotlin.screens.profile.services.ServiceProfile
import org.koin.android.ext.android.inject

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val services by inject<ServiceProfile>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDataBinding()

    }

    @SuppressLint("CheckResult")
    private fun userDataBinding() {
        services.userData().subscribe {
            response ->
            binding.username.text = response.name
        }
    }
}