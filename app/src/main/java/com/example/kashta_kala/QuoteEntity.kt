package com.example.kashta_kala

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val furnitureName: String,
    val totalCost: String
)