package com.example.bookappkotlin.screens.register.ui

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bookappkotlin.repositories.database.UserData
import com.example.bookappkotlin.repositories.database.UserViewModel
import com.example.bookappkotlin.databinding.ActivityRegisterBinding
import com.example.bookappkotlin.screens.home.ui.HomeActivity
import com.example.bookappkotlin.screens.register.model.UserRegister
import com.example.bookappkotlin.screens.register.services.RegisterService
import com.example.bookappkotlin.screens.register.viewmodel.RegisterViewModel
import com.example.bookappkotlin.screens.register.viewmodel.ViewModelRegister
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModelUser: UserViewModel
    private lateinit var viewModelRegister: ViewModelRegister

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var progressDialog: ProgressDialog

    private val userService by inject<RegisterService>()

    private lateinit var user: UserRegister
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)

        viewModelUser = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModelRegister = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.registerBtn.setOnClickListener {
            validateData()
        }
    }

    @SuppressLint("CheckResult")
    private fun validateData() {
        user = UserRegister(
            name = binding.nameEt.text.toString().trim(),
            email = binding.emailEt.text.toString().trim(),
            password = binding.passwordEt.text.toString().trim(),
            confirmPassword = binding.passwordEt.text.toString().trim()
        )

        val result = viewModelRegister.dataValidation(user)

        if (result == "NAME_EMPTY") {
            Toast.makeText(this, "Enter your name...", Toast.LENGTH_SHORT).show()
        } else if (result == "EMAIL_INVALID") {
            Toast.makeText(this, "Invalid Email Pattern...", Toast.LENGTH_SHORT).show()
        } else if (result == "PASSWORD_EMPTY") {
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show()
        } else if (result == "CONFIRM_PASSWORD_EMPTY") {
            Toast.makeText(this, "Confirm password...", Toast.LENGTH_SHORT).show()
        } else if (result == "PASSWORD_NOT_MATCH") {
            Toast.makeText(this, "Password doesn't match...", Toast.LENGTH_SHORT).show()
        } else {
            createUserAccount(user)
        }
    }

    private fun createUserAccount(user: UserRegister) {
        progressDialog.setMessage("Creating Account...")
        progressDialog.show()

        viewModelRegister.createUserAccount(user)

        viewModelRegister.booleanCreateAccountLiveData.observe(this) { response ->
            if (response) {
                redirectUserDashBoard(response)
                val userData = UserData(
                    id = 0,
                    name = user.name,
                    email = user.email,
                    password = user.password,
                    isLogged = true
                )
                viewModelUser.addUser(userData)

            } else {
                redirectUserDashBoard(response)
            }
        }

    }

    private fun redirectUserDashBoard(accepted: Boolean) {

        progressDialog.setMessage("Saving user info...")

        if (accepted) {
            Toast.makeText(this, "Account created...", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@RegisterActivity, HomeActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Failed saving user info account", Toast.LENGTH_SHORT).show()
        }
    }
}