package com.example.bookappkotlin.helpper

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookappkotlin.ApplicationConstants
import com.example.bookappkotlin.login.model.UserLogin
import com.example.bookappkotlin.login.repository.LoginResponse
import com.example.bookappkotlin.register.model.UserRegister
import com.example.bookappkotlin.splash.repository.SplashResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.collections.HashMap

interface AuthenticationHelper {
    val booleanLiveData : MutableLiveData<Boolean>
    fun databaseAuthentication(): FirebaseAuth
    fun liveDatabase() : FirebaseDatabase
    fun checkUser(response: SplashResponse)
    fun createUserAccount(user: UserRegister)
    fun loginUser(user: UserLogin, response: LoginResponse)
}

class DatabaseAuthenticationHelper : AuthenticationHelper, KoinComponent{

    private val firebaseAuth by inject<FirebaseAuth>()
    private val firebaseDatabase by inject<FirebaseDatabase>()


    val booleanLiveDataMutable = MutableLiveData<Boolean>()
    override val booleanLiveData: MutableLiveData<Boolean> = booleanLiveDataMutable


    override fun databaseAuthentication(): FirebaseAuth {
        return firebaseAuth
    }

    override fun liveDatabase() : FirebaseDatabase {
        return firebaseDatabase
    }

    override fun checkUser(response: SplashResponse) {

    }

    override fun createUserAccount(user: UserRegister) {

        databaseAuthentication().createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {

                val timestamp = System.currentTimeMillis()

                val uid = databaseAuthentication().uid

                val hashMap: HashMap<String, Any?> = HashMap()
                hashMap["uid"] = uid
                hashMap["email"] = user.email
                hashMap["name"] = user.name
                hashMap["password"] = user.password
                hashMap["profileImage"] = ""
                hashMap["userType"] = "user"
                hashMap["timestamp"] = timestamp

                val ref = liveDatabase().getReference(
                    ApplicationConstants.FIREBASE_USERS)
                ref.child(uid!!).setValue(hashMap)
                    .addOnSuccessListener {
                        booleanLiveData.postValue(true)
                    }
                    .addOnFailureListener{
                        booleanLiveData.postValue(false)
                    }
            }
            .addOnFailureListener{
                Log.i("Error", "temos um erro")
            }
    }

    override fun loginUser(user: UserLogin, response: LoginResponse) {

    }

}