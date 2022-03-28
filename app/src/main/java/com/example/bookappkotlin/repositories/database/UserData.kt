package com.example.bookappkotlin.repositories.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val email: String,
    val password:  String,
    val isLogged: Boolean
)