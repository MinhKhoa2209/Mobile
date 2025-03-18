package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {

    @Query( """SELECT * FROM airport WHERE name LIKE :query OR iata_code LIKE :query """)
    fun getAll(query: String): Flow<List<Airport>>
}
