package com.example.policybosscaller.Prefrence

import android.content.Context
import android.content.SharedPreferences
import com.example.policybosscaller.Utility.Constant
import java.text.SimpleDateFormat
import java.util.Date


/**
 * Created by Rajeev Ranjan on 29/03/2019.
 */
open class SharePrefernce (context: Context)  {

    var sharedPreferences: SharedPreferences
    var editor: SharedPreferences.Editor


    val LastStateKEY ="LastState"
    val  isInComingCallKEY = "isInComingCall"
    val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

    val PhoneNumerKEY = "PhoneNumerKEY"
    val CallTypeKey = "CallTypeKey"
    val WindowDialogStatus ="WindowDialogStattus"
    val SmsACTIVE ="smsACTIVE"
    val BootTimeKey ="BootTimeKey"

    init {
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREF, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }


    //region Commented Date in String format Save in Prefrences

    open fun saveOpenBootTime1(strDate: Date ){

        var  formatter  =  SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
        editor.putString(BootTimeKey,formatter.format(strDate))
        editor.commit()
    }

    open fun saveOpenBootTime1(strDate: String ){

        editor.putString(BootTimeKey,"")
        editor.commit()
    }
    open fun getBootCalculatedTime1(): String {

        return sharedPreferences.getString(BootTimeKey, "").toString()
    }
    //endregion
    open fun saveOpenBootTime(timeinMiiSec: Long ){

        editor.putLong(BootTimeKey,timeinMiiSec)
        editor.commit()
    }

    open fun getBootCalculatedTime(): Long {

        return sharedPreferences.getLong(BootTimeKey, 0)
    }
    open fun saveOverlayDialogStatus(bln: Boolean ){

        editor.putBoolean(WindowDialogStatus,bln)
        editor.commit()
    }
    open fun isOverlayPopup(): Boolean {

        return sharedPreferences.getBoolean(WindowDialogStatus, true)
    }


    open fun saveSmSActivation(bln: Boolean ){

        editor.putBoolean(SmsACTIVE,bln)
        editor.commit()
    }
    open fun isSmsACTIVE(): Boolean {

        return sharedPreferences.getBoolean(SmsACTIVE, true)
    }

    //open fun getInstance() : ApplicationPersistance()

    open fun saveLastState(lastState : Int){

        editor.putInt(LastStateKEY,lastState)
        editor.commit()
    }



    open fun readLastState(): Int {
        return sharedPreferences.getInt(LastStateKEY, 0)
    }

    open fun clearLastState(){

        editor.remove(LastStateKEY)
        editor.commit()
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