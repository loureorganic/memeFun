package com.example.bookappkotlin.screens.profile.services

import android.annotation.SuppressLint
import com.example.bookappkotlin.screens.profile.model.UserData
import com.example.bookappkotlin.screens.profile.repository.RepositoryProfile
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ServiceProfile {
    fun userData()  : Observable<UserData>
}

class ProfileServices () : ServiceProfile, KoinComponent {

    private val repository by inject<RepositoryProfile>()

    @SuppressLint("CheckResult")
    override fun userData() =  Observable.create<UserData>{ emitter ->
        val result =  repository.userData()

        result.subscribe{
            response ->
            val userProfileData = UserData(
                name = response.child("name").value  as String,
                email = response.child("email").value as String,
                password = response.child("password").value as String,
            )
            emitter.onNext(userProfileData)
        }


    }
}