package com.example.bookappkotlin.register.repository

import android.content.SharedPreferences
import com.example.bookappkotlin.ApplicationConstants
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.helpper.DatabaseGeneralHelper
import com.example.bookappkotlin.register.model.User
import org.koin.core.component.KoinComponent

typealias RegisterResponse = (success: Boolean) -> Unit

interface RegisterRepository {
    fun createUserAccount(user: User, response: RegisterResponse)
    fun isLogged(): Boolean
}

class UserRegisterRepository(
    private val preferences: SharedPreferences,
):RegisterRepository, KoinComponent {

    private val registerKey = "register"

    private val databaseAuthenticationHelper = DatabaseAuthenticationHelper()
    private val databaseGeneralHelper = DatabaseGeneralHelper()

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

                val ref = databaseGeneralHelper.liveDatabase().getReference(ApplicationConstants.FIREBASE_USERS)
                ref.child(uid!!).setValue(hashMap)
                    .addOnSuccessListener {
                    preferences.edit().putBoolean(registerKey, true).apply()
                    response(true)
                   }
                    .addOnFailureListener{
                        preferences.edit().putBoolean(registerKey, true).apply()
                        response(true)
                    }
            }
            .addOnFailureListener{
                response(false)
            }
    }

    override fun isLogged(): Boolean {
        return preferences.getBoolean(registerKey, false)
    }
}