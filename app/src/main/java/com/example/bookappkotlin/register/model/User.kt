package com.example.bookappkotlin.register.model

data class User(
    val name: String,
    val email : String,
    val password : String,
    val confirmPassword: String,
)