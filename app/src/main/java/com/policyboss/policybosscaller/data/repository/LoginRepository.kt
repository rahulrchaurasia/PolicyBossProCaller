package com.policyboss.policybosscaller.data.repository

import android.content.Context
import com.example.jetpackdemo.LoginModule.API.APIService
import com.example.policybosscaller.Prefrence.DataStoreManager

import com.utility.finmartcontact.core.requestentity.LoginRequestEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.Body

class LoginRepository (private val context : Context ,private val apiService : APIService) {


    suspend fun getLogin(loginRequestEntity: LoginRequestEntity)  = flow {


        val URL = "http://api.magicfinmart.com/api/Synclogin"
        val response = apiService.getLogin(URL,loginRequestEntity)


        //region For Handling Database
        if(response.isSuccessful) {

            if (response.body()?.StatusNo == 0) {

                response.body()?.let {
                   // demoDatabase.loginDao().insertLoginData(it.MasterData)

                    DataStoreManager(context.applicationContext).saveLoginData(fbaId = it.MasterData.FBAId.toString(),
                                    ssId = it.MasterData.ssid.toString(),
                                    parentId = it.MasterData.parentid.toString())

                }

            }
        }

        // endregion

        emit(response)

    }.flowOn(Dispatchers.IO)








}