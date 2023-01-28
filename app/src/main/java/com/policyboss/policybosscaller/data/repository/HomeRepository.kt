package com.policyboss.policybosscaller.data.repository

import android.content.Context
import com.example.jetpackdemo.LoginModule.API.APIService
import com.example.policybosscaller.Prefrence.DataStoreManager

import com.utility.finmartcontact.core.requestentity.LoginRequestEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.Body

class HomeRepository (private val context : Context, private val apiService : APIService) {




    suspend fun getUserConstant(@Body body: HashMap<String, String>)  = flow {



        val response = apiService.getConstant(body)


        //region For Handling Database
//        if(response.isSuccessful) {
//
//            if (response.body()?.StatusNo == 0) {
//
//                response.body()?.let {
//                    // demoDatabase.loginDao().insertLoginData(it.MasterData)
//
//
//                }
//
//            }
//        }

        // endregion

        emit(response)

    }.flowOn(Dispatchers.IO)







}