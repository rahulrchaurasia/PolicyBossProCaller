package com.policyboss.policybosscaller.Home

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.protobuf.Empty
import androidx.datastore.preferences.protobuf.NullValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.policybosscaller.Prefrence.DataStoreManager
import com.example.policybosscaller.Utility.Constant
import com.policyboss.policybosscaller.APIState
import com.policyboss.policybosscaller.data.model.DashboardData.ConstantEntity
import com.policyboss.policybosscaller.data.repository.HomeRepository
import com.policyboss.policybosscaller.data.response.ConstantDataResponse
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(var context: Context, var homeRepository: HomeRepository) : ViewModel(){


     var _constantData = MutableLiveData<List<ConstantEntity>>()

    val constantData : LiveData<List<ConstantEntity>>
        get() = _constantData

    init {

       // constantLiveData.postValue(homeRepository.userConstantEntity)
        viewModelScope.launch{
            homeRepository.getUserConstantList().collect{ item ->

                _constantData.postValue(item)

            }
        }

    }

    private val userConstantMutableStateFlow  : MutableStateFlow<APIState<ConstantDataResponse>> =  MutableStateFlow<APIState<ConstantDataResponse>>(
        APIState.Empty())

    val UserConstantStateFlow : StateFlow<APIState<ConstantDataResponse>>
        get() = userConstantMutableStateFlow




    fun saveOverlayStatus(status : Boolean) = viewModelScope.launch {

        DataStoreManager(context).saveOverlyStatus(status)
    }

    fun getOverlayStatus() : Flow<Boolean >{

//        viewModelScope.launch{
//
//        }

       return DataStoreManager(context).getOverlyStatus()
    }

    fun getOverlayStatus2() =

        viewModelScope.launch{
            DataStoreManager(context).getOverlyStatus().collect{

                return@collect
            }
        }


     fun saveUserConstant()   =  viewModelScope.launch{

       // var fbaID  = String()

         DataStoreManager(context).getFBAID().collect{
            var  fbaID = it
             Log.d(Constant.TAG ,"" + fbaID)

             val body = HashMap<String, String>()
             body["fbaid"] = fbaID


             try {
                 userConstantMutableStateFlow.value = APIState.Loading()

                 homeRepository.saveUserConstant(body)

                     .catch { ex ->

                         // emit(APIState.Failure(ex.message ?: "Unknown Error"))

                         userConstantMutableStateFlow.value = APIState.Failure(ex.message ?: "Unknown Error")

                         //loginMutableStateFlow.emit(APIState.Failure(ex.message ?: "Unknown Error"))

                     }.collect{ data ->

                         if(data.isSuccessful){
                             if(data.body()?.StatusNo == 0){


                                 userConstantMutableStateFlow.value = APIState.Success(data =  data.body()!!)

                                 // loginMutableStateFlow.emit(APIState.Success(data =  data.body()!!))
                             }else{

                                 userConstantMutableStateFlow.value = APIState.Failure(data.body()?.Message ?: Constant.ErrorMessage)
                                 //  loginMutableStateFlow.emit(APIState.Failure(data.body()?.Message ?: Constant.ErrorMessage))
                             }
                         }else{
                             userConstantMutableStateFlow.value = APIState.Failure(data.message() ?: Constant.ErrorMessage)
                             // loginMutableStateFlow.emit(APIState.Failure(data.body()?.Message ?: Constant.ErrorMessage))
                         }




                     }

             }catch (ex : Exception){

                 userConstantMutableStateFlow.value = APIState.Failure(ex.message ?: Constant.ErrorDefault)
                 //loginMutableStateFlow.emit(APIState.Failure(ex.message ?: Constant.ErrorDefault))

             }

         }


    }




}