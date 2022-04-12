package com.example.bookappkotlin.repositories.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface ViewModelUser{
    val readAllData: LiveData<List<UserData>>
    val repository: UserRepository
    fun addUser(user: UserData)
    fun deleteAllUsersData()
}



class UserViewModel(application: Application): AndroidViewModel(application), ViewModelUser {

    override val readAllData: LiveData<List<UserData>>
    override val repository: UserRepository

    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    override fun addUser(user: UserData){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun updateLoggedUserState(state: Boolean){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateLoggedUserState(state)
        }
    }

    fun deleteUserData(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUserData()
        }
    }

    override fun deleteAllUsersData(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsersData()
        }
    }
}