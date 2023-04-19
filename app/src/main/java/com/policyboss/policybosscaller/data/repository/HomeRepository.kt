package com.policyboss.policybosscaller.data.repository

import android.util.Log
import com.example.policybosscaller.Utility.Constant
import com.policyboss.policybosscaller.data.api.APIService
import com.policyboss.policybosscaller.data.db.database.CallerDatabase
import com.policyboss.policybosscaller.data.model.DashboardData.ConstantEntity


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.Body

class HomeRepository (
    private val apiService : APIService,
    private val demoDatabase: CallerDatabase
) {



    suspend fun saveUserConstant(@Body body: HashMap<String, String>)  = flow {



        val response = apiService.getConstant(body)


        //region For Handling Database
        if(response.isSuccessful) {

            if (response.body()?.StatusNo == 0) {

                response.body()?.let {
                    try {
                        demoDatabase.constantDao().saveConstantData(it.MasterData)

                    }catch (ex : Exception){

                        Log.d(Constant.TAG,"Error" + ex.message)
                    }


                }

            }
        }

        // endregion

        emit(response)

    }.flowOn(Dispatchers.IO)


    fun getUserConstantList() : List<ConstantEntity>{

        return demoDatabase.constantDao().getConstantData()
    }



}