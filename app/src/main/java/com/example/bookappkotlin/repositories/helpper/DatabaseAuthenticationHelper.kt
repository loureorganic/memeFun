package com.example.bookappkotlin.repositories.helpper

import android.util.Log
import com.example.bookappkotlin.ApplicationConstants
import com.example.bookappkotlin.screens.login.model.UserLogin
import com.example.bookappkotlin.screens.profile.model.UserData
import com.example.bookappkotlin.screens.register.model.UserRegister
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.collections.HashMap

interface AuthenticationHelper {
    fun checkUser(): Observable<Boolean>
    fun createUserAccount(user: UserRegister): Observable<Boolean>
    fun loginUser(user: UserLogin): Observable<Boolean>
    fun userData(): Observable<DataSnapshot>
    fun signOutUser()
}

class DatabaseAuthenticationHelper : AuthenticationHelper, KoinComponent {

    private val firebaseAuth by inject<FirebaseAuth>()
    private val firebaseDatabase by inject<FirebaseDatabase>()

    private fun databaseAuthentication(): FirebaseAuth {
        return firebaseAuth
    }

    private fun liveDatabase(): FirebaseDatabase {
        return firebaseDatabase
    }

    override fun checkUser() = Observable.create<Boolean> { emitter ->
        val firebaseUser = databaseAuthentication().currentUser
        if (firebaseUser == null) {
            emitter.onNext(false)
            Log.i("FIREBASE ERROR", "Firebase is null")
        } else {

            val ref = liveDatabase().getReference(ApplicationConstants.FIREBASE_USERS)
            ref.child(firebaseUser.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val userType = snapshot.child("userType").value
                        if (userType == "user") {
                            emitter.onNext(true)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.i("FIREBASE ERROR", "Error $error")
                    }
                })
        }
    }

    override fun createUserAccount(user: UserRegister) =
        Observable.create<Boolean> { emitter ->
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
                        ApplicationConstants.FIREBASE_USERS
                    )
                    ref.child(uid!!).setValue(hashMap)
                        .addOnSuccessListener {
                            emitter.onNext(true)
                        }
                        .addOnFailureListener {
                            emitter.onNext(false)
                        }
                }.addOnFailureListener {
                    Log.i("FIREBASE ERROR", "Error $it")
                }
        }

    override fun loginUser(user: UserLogin) = Observable.create<Boolean> { emitter ->
        databaseAuthentication().signInWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                emitter.onNext(true)
            }
            .addOnFailureListener {
                emitter.onNext(false)
            }
    }

    override fun userData() = Observable.create<DataSnapshot> { emitter ->

        val database = liveDatabase().getReference(ApplicationConstants.FIREBASE_USERS)

        database.get().addOnSuccessListener {
            if (it.exists()) {
                val uid = databaseAuthentication().currentUser?.uid
                if (uid != null) {
                    emitter.onNext(it.child(uid))
                }
            }
        }

    }

    override fun signOutUser() {
        databaseAuthentication().signOut()
    }
}