package com.example.policybosscaller.Utility

import android.app.ActivityManager.RunningTaskInfo
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager


object Constant {

    val TAG_HILT : String = "HiltDEMO"

    val TAG_KOTLIN : String = "KotlinDEMO"

    val TAG_Coroutine : String = "COROUTINE"

    val TAG_WEBVIEW : String = "WebViewDEMO"

    val TAG_WORKER : String = "WorkerDEMO"

    val TAG_SCANNER : String = "ScannerDEMO"

    const val KEY_COUNT_VALUE = "key_count"

    const val KEY_COUNT_VALUE1 = "key_count1"

     val KEY_DATA : String = "keydata"

    val ErrorMessage : String = "Error Occoured"

    val ErrorDefault : String = "Unknown Error"

    val NetworkError : String = "No Inerenet Connection!!"

    val PUSH_BROADCAST_ACTION : String = "com.example.policybosscaller.callDialog"

    const val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
    const val REQUEST_PERMISSION_SETTING = 101

    const val TAG = "CALLER_DATA"

    const val CALL_TYPE = "calltype"
    const val MissedCall = "Missed Call"
    const val EndCall = "End Call"
    const val Phone_NUMBER = "phoneNumber"

    var SHARED_PREF = "policyBossCallerPrefrence"

    const val LastState = "LastState"
    const val IsInComingCall = "IsInComingCall"

    const val CHANNEL_ID = "com.policyboss.policybosspro.caller_channel"
    const val CHANNEL_NAME =  "PolicyBossProCallerChannel"
    const val OVERLAY_SERVICE_KEY = "OVERLAYSERVICE"

    const val SERVICE_STOP= "STOP"
    const val SERVICE_START= "START"

    val PrivateCarUrl = "https://www.policyboss.com/UI22/car-insurance?ss_id=119225&fba_id=89433&v=20200609&sub_fba_id=0&ip_address=&mac_address=&app_version=policyboss-3.0.3&device_id=b4d1aefb362dfbed&product_id=1&login_ssid="

    val TwoWheelerUrl = "https://www.policyboss.com/UI22/two-wheeler-insurance?ss_id=119225&fba_id=89433&v=20200609&sub_fba_id=0&ip_address=&mac_address=&app_version=policyboss-3.0.3&device_id=b4d1aefb362dfbed&product_id=10&login_ssid="

    val HealthUrl = "https://www.policyboss.com/UI22/health-insurance?ss_id=119225&fba_id=89433&sub_fba_id=0&currentdate=20200325&ip_address=&app_version=policyboss-3.0.3&device_id=b4d1aefb362dfbed&login_ssid="

    val PrivateCar = "PRIVATECAR"
    val TwoWheeler = "TWOWHEELER"
    val Health  = "HEALTH"

    val EndActivity = "PopUpAfterCallEndActivity"

    const val IS_OVERLAYSCREEN= "IS_OVERLAYSCREEN"
    const val OVERLAY_DATA= "OVERLAY_DATA"
    const val BACKGROUND_DATA= "BACKGROUND_DATA"


    fun hideKeyBoard(view: View?, context: Context) {
        if (view != null) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun hasActivity(
        context: Context, pkg: String?,
        cls: String?
    ): Boolean { /*from   w w  w  . j ava2 s.  c  o  m*/
        var has = true
        val intent = Intent()
        intent.setClassName(pkg!!, cls!!)
        if (context.packageManager.resolveActivity(intent, 0) == null) {
            has = false
        }
        return has
    }



}