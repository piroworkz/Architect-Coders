package com.piroworkz.architectcoders.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "banners")
data class DbBanner(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val model: String,
    val bannerUrl: String,
)