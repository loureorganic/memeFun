package com.example.bookappkotlin.profile.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookappkotlin.ApplicationConstants
import com.example.bookappkotlin.helpper.AuthenticationHelper
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.google.firebase.database.*
import org.koin.core.component.KoinComponent

interface RepositoryProfile {
    val snapshotLiveDataMutable: MutableLiveData<DataSnapshot>
    fun userData()
}

class ProfileRepository(
): RepositoryProfile, KoinComponent {

    private lateinit var databaseAuthenticationHelper : AuthenticationHelper

    private val liveDataMutable = MutableLiveData<DataSnapshot>()
    override val snapshotLiveDataMutable: MutableLiveData<DataSnapshot> = liveDataMutable


    override fun userData() {
        databaseAuthenticationHelper = DatabaseAuthenticationHelper()

    }
}