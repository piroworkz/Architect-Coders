package com.piroworkz.architectcoders.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class DbUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uid: String,
    val name: String,
    val email: String,
    val phone: String,
    val photoUrl: String
)