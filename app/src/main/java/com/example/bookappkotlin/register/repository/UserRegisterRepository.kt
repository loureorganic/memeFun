package com.example.bookappkotlin.register.repository

import com.example.bookappkotlin.ApplicationConstants
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.register.model.User
import org.koin.core.component.KoinComponent

typealias RegisterResponse = (success: Boolean) -> Unit

interface RegisterRepository {
    fun createUserAccount(user: User, response: RegisterResponse)
}

class UserRegisterRepository():RegisterRepository, KoinComponent {

    private val databaseAuthenticationHelper = DatabaseAuthenticationHelper()

    override fun createUserAccount(user: User, response: RegisterResponse) {
        databaseAuthenticationHelper.databaseAuthentication().createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                val timestamp = System.currentTimeMillis()

                val uid = databaseAuthenticationHelper.databaseAuthentication().uid

                val hashMap: HashMap<String, Any?> = HashMap()
                hashMap["uid"] = uid
                hashMap["email"] = user.email
                hashMap["name"] = user.name
                hashMap["profileImage"] = ""
                hashMap["userType"] = "user"
                hashMap["timestamp"] = timestamp

                val ref = databaseAuthenticationHelper.liveDatabase().getReference(ApplicationConstants.FIREBASE_USERS)
                ref.child(uid!!).setValue(hashMap)
                    .addOnSuccessListener {
                    response(true)
                   }
                    .addOnFailureListener{
                        response(true)
                    }
            }
            .addOnFailureListener{
                response(false)
            }
    }
}