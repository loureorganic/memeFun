package com.example.bookappkotlin.profile.services

import com.example.bookappkotlin.login.repository.LoginRepository
import com.example.bookappkotlin.profile.repository.RepositoryProfile

interface ServiceProfile {
    fun userData()
}

class ProfileServices (
    private val repository: RepositoryProfile
) : ServiceProfile {
    override fun userData() {
        return repository.userData()
    }
}