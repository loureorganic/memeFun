package com.example.bookappkotlin.repositories.database

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<UserData>> = userDao.readAllData()

    suspend fun addUser(userData: UserData){
        userDao.addUser(userData)
    }

    suspend fun updateLoggedUserState(state: Boolean){
        userDao.updateLoggedUserState(currentState = state)
    }

    suspend fun deleteUserData(){
        userDao.deleteUserData()
    }

    suspend fun deleteAllUsersData(){
        userDao.deleteAllUsersData()
    }
}