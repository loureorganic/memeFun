package com.example.bookappkotlin.register.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bookappkotlin.database.UserData
import com.example.bookappkotlin.database.UserViewModel
import com.example.bookappkotlin.databinding.ActivityRegisterBinding
import com.example.bookappkotlin.home.ui.HomeActivity
import com.example.bookappkotlin.register.model.UserRegister
import com.example.bookappkotlin.register.repository.RegisterRepository
import com.example.bookappkotlin.register.services.RegisterService
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RegisterActivity : AppCompatActivity() {


    private lateinit var viewModelUser : UserViewModel

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var progressDialog: ProgressDialog

    private val userRepository by inject<RegisterRepository>() {
        parametersOf(getPreferences(MODE_PRIVATE))
    }

    private val userService by inject<RegisterService>() {
        parametersOf(userRepository)
    }

    private lateinit var user: UserRegister
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)

        viewModelUser = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.registerBtn.setOnClickListener {
            validateData()
        }



    }

    private fun validateData() {
        user = UserRegister(
            name = binding.nameEt.text.toString().trim(),
            email = binding.emailEt.text.toString().trim(),
            password = binding.passwordEt.text.toString().trim(),
            confirmPassword = binding.passwordEt.text.toString().trim()
        )

        if (user.name.isEmpty()) {
            Toast.makeText(this, "Enter your name...", Toast.LENGTH_SHORT).show()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            Toast.makeText(this, "Invalid Email Pattern...", Toast.LENGTH_SHORT).show()
        } else if (user.password.isEmpty()) {
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show()
        } else if (user.confirmPassword.isEmpty()) {
            Toast.makeText(this, "Confirm password...", Toast.LENGTH_SHORT).show()
        } else if (user.password != user.confirmPassword) {
            Toast.makeText(this, "Password doesn't match...", Toast.LENGTH_SHORT).show()
        } else {
            progressDialog.setMessage("Creating Account...")
            progressDialog.show()
            userService.createUserAccount(user, this::redirectUserDashBoard)
            val userData = UserData(id = 0, name = user.name, email =  user.email, password = user.password, isLogged = true)
            viewModelUser.addUser(userData)
        }
    }

    private fun redirectUserDashBoard(accepted: Boolean) {


        progressDialog.setMessage("Saving user info...")
        if (accepted) {
            Toast.makeText(this, "Account created...", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@RegisterActivity, HomeActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                this,
                "Failed saving user info account",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}