package com.example.bookappkotlin.screens.profile.services

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

    override fun userData() : Observable<UserData> {
        return repository.userData()
    }
}