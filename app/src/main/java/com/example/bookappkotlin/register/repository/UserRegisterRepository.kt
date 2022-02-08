package com.example.bookappkotlin.register.repository

import android.content.SharedPreferences
import com.example.bookappkotlin.register.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

typealias RegisterResponse = (success: Boolean) -> Unit

interface RegisterRepository {
    fun createUserAccount(user: User, response: RegisterResponse)
    fun isLogged(): Boolean
}

class UserRegisterRepository(
    private val firebaseAuth: FirebaseAuth,
    private val preferences: SharedPreferences,
):RegisterRepository {

    private val registerKey = "register"

    override fun createUserAccount(user: User, response: RegisterResponse) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                var timestamp = System.currentTimeMillis()

                val uid = firebaseAuth.uid

                val hashMap: HashMap<String, Any?> = HashMap()
                hashMap["uid"] = uid
                hashMap["email"] = user.email
                hashMap["name"] = user.name
                hashMap["profileImage"] = ""
                hashMap["userType"] = "user"
                hashMap["timestamp"] = timestamp

                val ref = FirebaseDatabase.getInstance().getReference("Users")
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