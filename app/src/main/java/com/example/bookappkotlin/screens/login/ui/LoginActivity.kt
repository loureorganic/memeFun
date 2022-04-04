package com.example.bookappkotlin.screens.login.ui

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bookappkotlin.repositories.database.UserViewModel
import com.example.bookappkotlin.databinding.ActivityLoginBinding
import com.example.bookappkotlin.screens.home.ui.HomeActivity
import com.example.bookappkotlin.screens.login.model.UserLogin
import com.example.bookappkotlin.screens.login.utils.LoginConstants
import com.example.bookappkotlin.screens.login.viewmodel.LoginViewModel
import com.example.bookappkotlin.screens.login.viewmodel.ViewModelLogin
import com.example.bookappkotlin.screens.register.ui.RegisterActivity
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModelUser : UserViewModel
    private lateinit var viewModel: ViewModelLogin

    private lateinit var binding: ActivityLoginBinding

    private lateinit var progressDialog: ProgressDialog

    private val viewModelLogin by inject<ViewModelLogin>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        viewModelUser = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel = viewModelLogin

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.noAccountTv.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.loginBtn.setOnClickListener{
            val user = UserLogin(
                email = binding.emailEt.text.toString().trim(),
                password = binding.passwordEt.text.toString().trim()
            )
            validateData(user = user)
        }

    }

    @SuppressLint("CheckResult")
    private fun validateData(user: UserLogin) {

        val result = viewModel.dataValidation(user)

        if(result == LoginConstants.INVALID_EMAIL){
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
        }
        else if(result == LoginConstants.EMPTY_PASSWORD){
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show()
        }else if(result == LoginConstants.VALID){
            loginAccount(user)
        }
    }

    private fun loginAccount(user: UserLogin) {
       viewModel.loginUser(user)

       viewModel.booleanLoginAccountLiveData.observe(this){
                logged -> redirectDashBoardUser(logged = logged)
        }
    }

    private fun redirectDashBoardUser(logged: Boolean) {
        if(logged) {
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            finish()
        }else{
            Toast.makeText(this, "Email or password are invalid", Toast.LENGTH_SHORT).show()
        }
    }

}