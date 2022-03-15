package com.example.bookappkotlin.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<UserData>>
    private val repository: UserRepository

    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: UserData){
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

    fun deleteAllUsersData(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsersData()
        }
    }
}