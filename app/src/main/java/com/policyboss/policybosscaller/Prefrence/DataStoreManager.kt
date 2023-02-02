package com.example.policybosscaller.Prefrence

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore


import com.example.policybosscaller.Utility.Constant
import kotlinx.coroutines.flow.*


class DataStoreManager constructor (val context: Context){


    companion object {

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "POLICYBOSS_CALLER_DATASTORE")


        val LastState = intPreferencesKey(Constant.LastState)
        val IsInComingCall = booleanPreferencesKey(Constant.IsInComingCall)

        val FBAData = stringPreferencesKey("FBADATA")
        val SSIDData = stringPreferencesKey("SSIDData")
        val PARENTDATA = stringPreferencesKey("PARENTID")

        val IsOverlayShow = booleanPreferencesKey(Constant.IsOverlayShow)

       // val LoginData= stringPreferencesKey(Constant.loginData)

    }

    suspend fun clearAll() {
        context.dataStore.edit {
            it.clear()
        }
    }
    suspend fun saveLoginData(fbaId : String,ssId : String, parentId : String ) {
        context.dataStore.edit { preferences ->
            preferences[FBAData] = fbaId
            preferences[SSIDData] = ssId
            preferences[PARENTDATA] = parentId

        }
    }


     fun getFBAID(): Flow<String> =

        context.dataStore.data.catch {

            if(this is Exception){
                emit(emptyPreferences())
            }
        }.map { preferences ->
           preferences[FBAData] ?: "0"

        }

        //context.dataStore.data.map { it[FBAData]?: "0" }


    //2

    suspend fun saveSSId(ssId : String ) {
        context.dataStore.edit { preferences ->
            preferences[SSIDData] = ssId

        }
    }
    fun getSSID(): Flow<String> =

        context.dataStore.data.catch {

            if(this is Exception){
                emit(emptyPreferences())
            }
        }.map { preferences ->
            preferences[SSIDData] ?: "0"

        }

    //3

    suspend fun saveParentID(parentId : String ) {
        context.dataStore.edit { preferences ->
            preferences[PARENTDATA] = parentId

        }
    }
    fun getParentID(): Flow<String> =

        context.dataStore.data.catch {

            if(this is Exception){
                emit(emptyPreferences())
            }
        }.map { preferences ->
            preferences[PARENTDATA] ?: "0"

        }

//    suspend fun updateSortOrder(loginEntity : LoginEntity ) {
//        context.dataStore.edit { preferences ->
//            preferences[Constant.loginData] = loginEntity
//        }
//    }


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

    suspend fun saveOverlyStatus(isShow :Boolean ) {
        context.dataStore.edit { preferences ->
            preferences[IsOverlayShow] = isShow


        }
    }

    fun getOverlyStatus(): Flow<Boolean> =

        context.dataStore.data.catch {

            if(this is Exception){
                emit(emptyPreferences())
            }
        }.map { preferences ->
            preferences[IsOverlayShow] ?: false

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

