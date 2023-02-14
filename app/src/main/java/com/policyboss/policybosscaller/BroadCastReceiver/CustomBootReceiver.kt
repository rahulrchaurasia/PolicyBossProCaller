package com.policyboss.policybosscaller.BroadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.policybosscaller.Prefrence.SharePrefernce
import com.example.policybosscaller.Utility.Constant
import com.example.policybosscaller.Utility.Utility
import com.policyboss.policybosscaller.OverlayDemo.OverlayService
import com.policyboss.policybosscaller.popup.PopUpAfterCallEndActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

class CustomBootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action: String? = intent?.getAction()

       // toast(context, "Device is Reboot")
        //Log.d(Constant.TAG,"Device is Reboot")

        if (action != null) {
            if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
                // TO-DO: Code to handle BOOT COMPLETED EVENT
                // TO-DO: I can start an service.. display a notification... start an activity
                Log.d(Constant.TAG,"Device is Reboot")
                OverlayService.startServiceAfterBoot(  context = OverlayService.getServiceContext(context!!))
                val calculatedtTime = Calendar.getInstance()
                calculatedtTime.add(Calendar.MINUTE, + 5)


                SharePrefernce(context!!).saveOpenBootTime(calculatedtTime.timeInMillis)
              //  SharePrefernce(context!!).saveOpenBootTime1(Utility.getDate(calculatedtTime.time))

                Log.d(Constant.TAG,"Calaculated Time  ${Utility.getDate(calculatedtTime.time)}")

            }
        }
    }


}