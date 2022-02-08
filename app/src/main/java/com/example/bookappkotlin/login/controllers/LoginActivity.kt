package com.example.bookappkotlin.login.controllers

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappkotlin.home.HomeActivity
import com.example.bookappkotlin.databinding.ActivityLoginBinding
import com.example.bookappkotlin.login.model.User
import com.example.bookappkotlin.login.repository.UserLoginRepository
import com.example.bookappkotlin.login.services.UserLoginServices
import com.example.bookappkotlin.register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    //viewbinding

    private lateinit var binding: ActivityLoginBinding

    private lateinit var progressDialog: ProgressDialog

    private lateinit var userService: UserLoginServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.noAccountTv.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        //handleClick, begin login

        binding.loginBtn.setOnClickListener{
            validateData()
        }
    }


    //has the responsibility to start all the application
    private fun setup() {
        val preferences = getPreferences(MODE_PRIVATE)
        val firebase = FirebaseAuth.getInstance()
        val repository = UserLoginRepository(firebase, preferences)
        userService = UserLoginServices(repository)
    }

    private fun validateData() {

        val user: User = User(
            email = binding.emailEt.text.toString().trim(),
            password = binding.passwordEt.text.toString().trim()
        )

        if(!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()){
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
        }
        else if(user.password.isEmpty()){
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show()
        }else{
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