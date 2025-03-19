package com.example.flightsearch.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.io.IOException

class DataStoreManager(context: Context) {

    private val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        produceFile = { context.preferencesDataStoreFile("flight_search_prefs") },
        corruptionHandler = null,
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    )

    companion object {
        private val SEARCH_QUERY_KEY = stringPreferencesKey("search_query")
    }

    val searchQueryFlow: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[SEARCH_QUERY_KEY] ?: ""
        }

    suspend fun saveSearchQuery(query: String) {
        dataStore.edit { preferences ->
            preferences[SEARCH_QUERY_KEY] = query
        }
    }
}
