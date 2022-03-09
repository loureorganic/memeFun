package com.example.bookappkotlin.profile.repository

import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.helpper.DatabaseGeneralHelper
import com.google.firebase.database.*
import org.koin.core.component.KoinComponent

interface RepositoryProfile {
   fun userData()
}

class ProfileRepository(
): RepositoryProfile, KoinComponent {

    //depedency
    private lateinit var database: DatabaseReference

    private val databaseGeneralHelper = DatabaseGeneralHelper()


    override fun userData() {
        database =  databaseGeneralHelper.liveDatabase().getReference("Users")

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