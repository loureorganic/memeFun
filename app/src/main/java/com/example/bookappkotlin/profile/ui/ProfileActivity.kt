package com.example.bookappkotlin.profile.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappkotlin.databinding.ActivityHomeBinding
import com.example.bookappkotlin.databinding.ActivityProfileBinding
import com.google.firebase.database.*
import kotlin.reflect.typeOf

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}