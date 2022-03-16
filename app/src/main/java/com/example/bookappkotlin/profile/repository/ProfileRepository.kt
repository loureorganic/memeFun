package com.example.bookappkotlin.profile.repository

import com.example.bookappkotlin.ApplicationConstants
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.google.firebase.database.*
import org.koin.core.component.KoinComponent

interface RepositoryProfile {
   fun userData()
}

class ProfileRepository(
): RepositoryProfile, KoinComponent {

    //depedency
    private lateinit var database: DatabaseReference

    private val databaseAuthenticationHelper = DatabaseAuthenticationHelper()

    override fun userData() {
        database =  databaseAuthenticationHelper.liveDatabase().getReference(ApplicationConstants.FIREBASE_USERS)

        database.database.reference.child("Users").addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
        )
        database.get().addOnSuccessListener {
            if(it.exists()){
                val fullName = it.child("name").value
            }
        }
    }
}