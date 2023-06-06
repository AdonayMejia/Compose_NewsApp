package com.example.compose_newsapp.model.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.compose_newsapp.model.datamodel.Filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = FILTER_SETTINGS)
    private val dataStore: DataStore<Preferences> = context.dataStore

    suspend fun setSectionFilter(filter: Filter) {
        dataStore.edit {
            it[SECTION_FILTER] = filter.filterName
        }
    }

    fun getFilter(): Flow<Filter>{
        return dataStore.data.map {
            val filterName = it[SECTION_FILTER].orEmpty()
            Filter(filterName)
        }
    }



    companion object{
        private const val FILTER_SETTINGS = "filter_settings"
        private val SECTION_FILTER = stringPreferencesKey("sectionFilter")
    }
}