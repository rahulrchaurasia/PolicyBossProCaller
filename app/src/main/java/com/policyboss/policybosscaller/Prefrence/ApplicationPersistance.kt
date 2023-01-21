package com.example.policybosscaller.Prefrence

import android.content.Context
import android.content.SharedPreferences
import android.provider.ContactsContract.CommonDataKinds.Phone
import com.example.policybosscaller.OverlayDemo.CallType
import com.example.policybosscaller.Utility.Constant


/**
 * Created by Rajeev Ranjan on 29/03/2019.
 */
open class ApplicationPersistance (context: Context)  {

    var sharedPreferences: SharedPreferences
    var editor: SharedPreferences.Editor


    val LastStateKEY ="LastState"
    val  isInComingCallKEY = "isInComingCall"
    val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

    val PhoneNumerKEY = "PhoneNumerKEY"
    val CallTypeKey = "CallTypeKey"

    init {
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREF, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }



   //open fun getInstance() : ApplicationPersistance()

    open fun saveLastState(lastState : Int){

        editor.putInt(LastStateKEY,lastState)
        editor.commit()
    }

    open fun readLastState(): Int {
        return sharedPreferences.getInt(LastStateKEY, 0)
    }


    open fun saveIsCallInComming(isInComingCall: Boolean) {
        editor.putBoolean(isInComingCallKEY, isInComingCall)
        editor.commit()
    }

    open fun readIsCallInComming(): Boolean {
        return sharedPreferences.getBoolean(isInComingCallKEY, false)
    }


    open fun savePhoneCallType(callType: String? ,phone: String? ){

        editor.putString(PhoneNumerKEY, phone?: "")
        editor.putString(CallTypeKey, callType ?: "")
        editor.commit()
    }

    open fun getPhoneNumber() : String {

        return sharedPreferences.getString(PhoneNumerKEY,"") ?: ""
    }

    open fun saveCallType(callType: String){

        editor.putString(CallTypeKey, callType)
        editor.commit()
    }

    open fun getCallType() : String {

        return sharedPreferences.getString(CallTypeKey,"") ?: ""
    }

    fun clear(){

        editor.clear()
        editor.commit()
    }
}