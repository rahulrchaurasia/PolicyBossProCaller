package com.policyboss.policybosscaller.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.policybosscaller.Prefrence.DataStoreManager
import com.example.policybosscaller.Utility.Constant
import com.policyboss.policybosscaller.APIState
import com.policyboss.policybosscaller.data.repository.LoginRepository
import com.policyboss.policybosscaller.data.response.ConstantDataResponse
import com.policyboss.policybosscaller.data.response.LoginResponse
import com.utility.finmartcontact.core.requestentity.LoginRequestEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel (var loginRepository: LoginRepository) : ViewModel(){


    private val loginMutableStateFlow  :  MutableStateFlow<APIState<LoginResponse>> =  MutableStateFlow<APIState<LoginResponse>>(APIState.Empty())

    val LoginStateFlow : StateFlow<APIState<LoginResponse>>
    get() = loginMutableStateFlow




    fun getLoginUsingFlow(loginRequestEntity: LoginRequestEntity) = viewModelScope.launch {

        loginMutableStateFlow.value = APIState.Loading()

        try {


            loginRepository.getLogin(loginRequestEntity)

                .catch { ex ->

                                 // emit(APIState.Failure(ex.message ?: "Unknown Error"))

                    loginMutableStateFlow.value = APIState.Failure(ex.message ?: "Unknown Error")

                    //loginMutableStateFlow.emit(APIState.Failure(ex.message ?: "Unknown Error"))

                }.collect{ data ->

                    if(data.isSuccessful){
                        if(data.body()?.StatusNo == 0){


                            loginMutableStateFlow.value = APIState.Success(data =  data.body()!!)

                           // loginMutableStateFlow.emit(APIState.Success(data =  data.body()!!))
                        }else{

                            loginMutableStateFlow.value = APIState.Failure(data.body()?.Message ?: Constant.ErrorMessage)
                          //  loginMutableStateFlow.emit(APIState.Failure(data.body()?.Message ?: Constant.ErrorMessage))
                        }
                    }else{
                        loginMutableStateFlow.value = APIState.Failure(data.message() ?: Constant.ErrorMessage)
                       // loginMutableStateFlow.emit(APIState.Failure(data.body()?.Message ?: Constant.ErrorMessage))
                    }




                }

        }catch (ex : Exception){

            loginMutableStateFlow.value = APIState.Failure(ex.message ?: Constant.ErrorDefault)
            //loginMutableStateFlow.emit(APIState.Failure(ex.message ?: Constant.ErrorDefault))

        }

    }





}