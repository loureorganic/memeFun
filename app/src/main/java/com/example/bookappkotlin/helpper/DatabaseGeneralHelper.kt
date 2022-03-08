package com.example.bookappkotlin.helpper


import com.google.firebase.database.FirebaseDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface DatabaseGeneral{
 fun liveDatabase(): FirebaseDatabase
}

class DatabaseGeneralHelper : DatabaseGeneral, KoinComponent {
    private val firebaseDatabase by inject<FirebaseDatabase>()

    override fun liveDatabase() : FirebaseDatabase {
        return firebaseDatabase
    }
}