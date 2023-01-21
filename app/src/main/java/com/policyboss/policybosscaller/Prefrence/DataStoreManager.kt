package com.example.policybosscaller.Prefrence

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.policybosscaller.Utility.Constant


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class DataStoreManager (val context: Context){

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATASTORE")

    companion object {

        val LastState = intPreferencesKey(Constant.LastState)
        val IsInComingCall = booleanPreferencesKey(Constant.IsInComingCall)


    }

    suspend fun saveLastState(lastState : Int ) {
        context.dataStore.edit {

            it[LastState] = lastState



        }
    }

    fun readLastState(): Flow<Int> {
        return context.dataStore.data.map { preferences ->
            preferences[LastState] ?: -1
        }
    }


    suspend fun saveIsCallInComming( isInComingCall : Boolean ) {
        context.dataStore.edit {


            it[IsInComingCall] = isInComingCall


        }
    }

    fun readIsCallInComming(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[IsInComingCall] ?: false
        }
    }




    suspend fun clearIntPref(){
        context.dataStore.edit { preferences ->
            preferences.remove(LastState)
            preferences.remove(IsInComingCall)
        }
    }


}

