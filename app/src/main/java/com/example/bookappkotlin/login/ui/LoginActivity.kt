package com.example.bookappkotlin.login.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappkotlin.databinding.ActivityLoginBinding
import com.example.bookappkotlin.home.ui.HomeActivity
import com.example.bookappkotlin.login.model.User
import com.example.bookappkotlin.login.services.LoginService
import com.example.bookappkotlin.register.ui.RegisterActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var progressDialog: ProgressDialog

    private val userRegisterRepository by inject<LoginService>(){
        parametersOf(getPreferences(MODE_PRIVATE))
    }

    private val userService by inject<LoginService>(){
        parametersOf(userRegisterRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

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