package com.example.bookappkotlin.login.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bookappkotlin.database.UserViewModel
import com.example.bookappkotlin.databinding.ActivityLoginBinding
import com.example.bookappkotlin.home.ui.HomeActivity
import com.example.bookappkotlin.login.model.UserLogin
import com.example.bookappkotlin.login.services.LoginService
import com.example.bookappkotlin.register.ui.RegisterActivity
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModelUser : UserViewModel

    private lateinit var binding: ActivityLoginBinding

    private lateinit var progressDialog: ProgressDialog

    private val userService by inject<LoginService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModelUser = ViewModelProvider(this).get(UserViewModel::class.java)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.noAccountTv.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.loginBtn.setOnClickListener{
            validateData()
        }
    }

    private fun validateData() {

        val user = UserLogin(
            email = binding.emailEt.text.toString().trim(),
            password = binding.passwordEt.text.toString().trim()
        )

        if(!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()){
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
        }
        else if(user.password.isEmpty()){
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show()
        }else{
            //dependency
            userService.loginUser(user, this::redirectDashBoardUser)
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