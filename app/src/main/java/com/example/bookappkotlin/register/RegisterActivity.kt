package com.example.bookappkotlin.register

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappkotlin.home.HomeActivity
import com.example.bookappkotlin.databinding.ActivityRegisterBinding
import com.example.bookappkotlin.register.model.User
import com.example.bookappkotlin.register.repository.UserRegisterRepository
import com.example.bookappkotlin.register.services.UserRegisterServices
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var progressDialog: ProgressDialog

    //dependency
    private lateinit var userService: UserRegisterServices
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

        binding.registerBtn.setOnClickListener {
            validateData()
        }
    }

    private fun setup() {
        //dependency
        val preferences = getPreferences(Context.MODE_PRIVATE)
        val firebase = FirebaseAuth.getInstance()
        val repository = UserRegisterRepository(firebase, preferences)
        userService = UserRegisterServices(repository)
    }

    private fun validateData() {
        user = User(
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
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();
            userService.createUserAccount(user, this::redirectUserDashBoard);
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