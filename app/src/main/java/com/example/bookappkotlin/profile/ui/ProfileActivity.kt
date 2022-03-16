package com.example.bookappkotlin.profile.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappkotlin.databinding.ActivityProfileBinding
import com.example.bookappkotlin.profile.model.UserData
import com.example.bookappkotlin.profile.repository.ProfileRepository
import com.example.bookappkotlin.profile.repository.RepositoryProfile
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var profileRepository : RepositoryProfile
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileRepository = ProfileRepository()
        profileRepository.userData()
        firebaseAuth = FirebaseAuth.getInstance()
        initObservables()



    }

    private fun initObservables() {
        val uidUser = firebaseAuth.currentUser!!.uid
        profileRepository.snapshotLiveDataMutable.observe(this){

            val userProfileData = UserData(
                name = it.child(uidUser).child("name").value as String,
                email = it.child(uidUser).child("email").value as String,
                password = it.child(uidUser).child("password").value as String,
                )
            binding.username.text = userProfileData.name
        }
    }


}