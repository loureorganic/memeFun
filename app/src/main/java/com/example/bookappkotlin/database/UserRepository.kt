package com.example.bookappkotlin.database

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<UserData>> = userDao.readAllData()

    suspend fun addUser(userData: UserData){
        userDao.addUser(userData)
    }
}