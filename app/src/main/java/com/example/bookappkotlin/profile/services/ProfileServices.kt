package com.example.bookappkotlin.profile.services

import com.example.bookappkotlin.profile.model.UserData
import com.example.bookappkotlin.profile.repository.RepositoryProfile
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ServiceProfile {
    fun userData()  : Observable<UserData>
}

class ProfileServices () : ServiceProfile, KoinComponent {

    private val repository by inject<RepositoryProfile>()

    override fun userData() : Observable<UserData> {
        return repository.userData()
    }
}