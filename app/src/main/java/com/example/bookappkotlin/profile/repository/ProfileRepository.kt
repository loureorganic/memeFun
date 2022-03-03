package com.example.bookappkotlin.profile.repository


import com.google.firebase.auth.FirebaseAuth
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RepositoryProfile {
   fun userData()
}

class ProfileRepository(
): RepositoryProfile, KoinComponent {
    private val value by inject<FirebaseAuth>()

    override fun userData() {
        value.currentUser
    }
}