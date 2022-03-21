package com.example.bookappkotlin.helpper

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookappkotlin.ApplicationConstants
import com.example.bookappkotlin.login.model.UserLogin
import com.example.bookappkotlin.register.model.UserRegister
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
    val booleanLiveData: MutableLiveData<Boolean>
    val snapshotLiveDataMutable: MutableLiveData<DataSnapshot>
    fun databaseAuthentication(): FirebaseAuth
    fun liveDatabase(): FirebaseDatabase
    fun checkUser(): Observable<Boolean>
    fun createUserAccount(user: UserRegister): Observable<Boolean>
    fun loginUser(user: UserLogin): Observable<Boolean>
    fun userData()
}

class DatabaseAuthenticationHelper : AuthenticationHelper, KoinComponent {

    private val firebaseAuth by inject<FirebaseAuth>()
    private val firebaseDatabase by inject<FirebaseDatabase>()

    private val booleanLiveDataMutable = MutableLiveData<Boolean>()
    override val booleanLiveData: MutableLiveData<Boolean> = booleanLiveDataMutable

    private val liveDataMutable = MutableLiveData<DataSnapshot>()
    override val snapshotLiveDataMutable: MutableLiveData<DataSnapshot> = liveDataMutable

    override fun databaseAuthentication(): FirebaseAuth {
        return firebaseAuth
    }

    override fun liveDatabase(): FirebaseDatabase {
        return firebaseDatabase
    }

    override fun checkUser() = Observable.create<Boolean> { emmiter ->
        val firebaseUser = databaseAuthentication().currentUser
        if (firebaseUser == null) {
            emmiter.onNext(false)
            Log.i("FIREBASE ERROR", "Firebase is null")
        } else {

            val ref = liveDatabase().getReference(ApplicationConstants.FIREBASE_USERS)
            ref.child(firebaseUser.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val userType = snapshot.child("userType").value
                        if(userType == "user"){
                            emmiter.onNext(true)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.i("FIREBASE ERROR", "Error $error")
                    }
                })
        }
    }

    override fun createUserAccount(user: UserRegister) =
        Observable.create<Boolean> { emmiter ->
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
                            emmiter.onNext(true)
                        }
                        .addOnFailureListener {
                            emmiter.onNext(false)
                        }
                }.addOnFailureListener {
                    Log.i("FIREBASE ERROR", "Error $it")
                }
        }

    override fun loginUser(user: UserLogin) = Observable.create<Boolean> { emmiter ->
        databaseAuthentication().signInWithEmailAndPassword(user.email,user.password)
            .addOnSuccessListener {
                emmiter.onNext(true)
            }
            .addOnFailureListener{
                emmiter.onNext(false)
            }
    }

    override fun userData() {

        val database = liveDatabase().getReference(ApplicationConstants.FIREBASE_USERS)

        database.get().addOnSuccessListener {
            if (it.exists()) {
                snapshotLiveDataMutable.postValue(it)
            }
        }
    }
}