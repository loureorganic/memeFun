package com.example.bookappkotlin.screens.profile.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappkotlin.databinding.ActivityProfileBinding
import com.example.bookappkotlin.screens.profile.viewmodel.ViewModelProfile
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel by inject<ViewModelProfile>() {
        parametersOf(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        userDataBinding()
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    private fun userDataBinding() {
        viewModel.userData()
        viewModel.errorDataProfileAccount.observe(this) { error ->
            viewModel.dataProfileAccountLiveData.observe(this) { response ->
                binding.username.text = response.name
            }

        }
    }
}