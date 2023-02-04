package com.example.jetpackdemo.LoginModule.API


import com.policyboss.policybosscaller.data.response.ConstantDataResponse
import com.example.jetpackdemo.RetrofitHelper
import com.policyboss.policybosscaller.data.response.LoginResponse
import com.utility.finmartcontact.core.requestentity.LoginRequestEntity
import retrofit2.Response
import retrofit2.http.*

interface APIService {



    @Headers("token:"+ RetrofitHelper.token)
    @POST
    suspend fun getLogin(@Url url: String,
        @Body body: LoginRequestEntity
    ) : Response<LoginResponse>


    @Headers("token:"+ RetrofitHelper.token)
    @POST("/quote/Postfm/user-constant-pb")
    suspend fun getConstant(@Body body: HashMap<String, String>) : Response<ConstantDataResponse>


//    @Headers("token:"+ RetrofitHelper.token)
//    @POST("/quote/Postfm/user-constant-pb")
//    suspend fun getConstant(@Body body: HashMap<String, String>) : Response<ConstantDataResponse>


}