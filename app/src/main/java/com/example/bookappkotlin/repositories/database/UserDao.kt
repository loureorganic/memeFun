package com.example.bookappkotlin.repositories.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userData: UserData)

    @Update
    suspend fun updateLoggedUserState(currentState: Boolean){
    }

    @Query("DELETE FROM user_data")
    suspend fun deleteAllUsersData(){

    }

    @Delete
    suspend fun deleteUserData(){
    }

    @Query("SELECT * FROM user_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<UserData>>
}