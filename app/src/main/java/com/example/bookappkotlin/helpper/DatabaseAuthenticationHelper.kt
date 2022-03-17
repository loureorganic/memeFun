package com.example.bookappkotlin.helpper

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookappkotlin.ApplicationConstants
import com.example.bookappkotlin.login.model.UserLogin
import com.example.bookappkotlin.login.repository.LoginResponse
import com.example.bookappkotlin.register.model.UserRegister
import com.example.bookappkotlin.splash.repository.SplashResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.collections.HashMap

interface AuthenticationHelper {
    val booleanLiveData : MutableLiveData<Boolean>
    val snapshotLiveDataMutable: MutableLiveData<DataSnapshot>
    fun databaseAuthentication(): FirebaseAuth
    fun liveDatabase() : FirebaseDatabase
    fun checkUser(response: SplashResponse)
    fun createUserAccount(user: UserRegister) : Observable<Boolean>
    fun loginUser(user: UserLogin, response: LoginResponse)
    fun userData()
}

class DatabaseAuthenticationHelper : AuthenticationHelper, KoinComponent{

    private val firebaseAuth by inject<FirebaseAuth>()
    private val firebaseDatabase by inject<FirebaseDatabase>()
    lateinit var  mostPopular: Observable<Boolean>

    val booleanLiveDataMutable = MutableLiveData<Boolean>()
    override val booleanLiveData: MutableLiveData<Boolean> = booleanLiveDataMutable

    private val liveDataMutable = MutableLiveData<DataSnapshot>()
    override val snapshotLiveDataMutable: MutableLiveData<DataSnapshot> = liveDataMutable

   // val mostPopular: Observable<String> = Observable.just("Here")




    override fun databaseAuthentication(): FirebaseAuth {
        return firebaseAuth
    }

    override fun liveDatabase() : FirebaseDatabase {
        return firebaseDatabase
    }

    override fun checkUser(response: SplashResponse) {
    }

    override fun createUserAccount(user: UserRegister) = Observable.create<Boolean>{
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
            }
            .addOnFailureListener{
            }
    }


    override fun loginUser(user: UserLogin, response: LoginResponse) {

    }

    @SuppressLint("CheckResult")
    override fun userData() {

        val database =  liveDatabase().getReference(ApplicationConstants.FIREBASE_USERS)

        database.get().addOnSuccessListener {
            if(it.exists()){
                snapshotLiveDataMutable.postValue(it)
            }
        }
    }

}