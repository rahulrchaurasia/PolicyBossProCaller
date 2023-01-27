package com.policyboss.policybosscaller.Home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.policybosscaller.Prefrence.DataStoreManager
import com.example.policybosscaller.Utility.Constant
import com.policyboss.policybosscaller.APIState
import com.policyboss.policybosscaller.data.response.ConstantDataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch

class HomeViewModel(var context: Context) : ViewModel(){


    private val userConstantMutableStateFlow  : MutableStateFlow<APIState<ConstantDataResponse>> =  MutableStateFlow<APIState<ConstantDataResponse>>(
        APIState.Empty())

    val UserConstantStateFlow : StateFlow<APIState<ConstantDataResponse>>
        get() = userConstantMutableStateFlow



    fun getUserConstant(fbaID : String){

        val body = HashMap<String, String>()
        body["fbaid"] = fbaID



    }
}