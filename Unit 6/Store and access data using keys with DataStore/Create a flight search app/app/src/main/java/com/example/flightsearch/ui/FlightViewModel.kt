package com.example.flightsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightApplication
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.AirportDao
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FavoriteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FlightViewModel(
    private val airportDao: AirportDao,
    private val favoriteDao: FavoriteDao
) : ViewModel() {

    fun getAllAirports(query: String): Flow<List<Airport>> = airportDao.getAll("%$query%")

    suspend fun isFavorite(airportId: Int): Boolean {
        return favoriteDao.getFavoriteById(airportId) != null
    }

    suspend fun toggleFavorite(airport: Airport) {
        if (isFavorite(airport.id)) {
            favoriteDao.deleteFavoriteById(airport.id)
        } else {
            favoriteDao.insertFavorite(Favorite(airport.id, airport.iata_code, airport.name))
        }
    }
    fun getFavoriteAirports(): Flow<List<Airport>> {
        return favoriteDao.getAllFavorites().map { favorites ->
            favorites.flatMap { fav ->
                airportDao.getAirportByCode(fav.departure_code, fav.destination_code)
            }
        }
    }


    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightApplication)
                FlightViewModel(application.database.airportDao(), application.database.favoriteDao())
            }
        }
    }
}
