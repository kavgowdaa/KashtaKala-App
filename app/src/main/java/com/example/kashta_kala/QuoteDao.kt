package com.example.kashta_kala

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQuote(quote: QuoteEntity)

    @Query("SELECT * FROM quotes")
    suspend fun getAllQuotes(): List<QuoteEntity>
}