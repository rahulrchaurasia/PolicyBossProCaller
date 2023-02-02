package com.policyboss.policybosscaller.BroadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.policybosscaller.Utility.Constant
import com.policyboss.policybosscaller.OverlayDemo.OverlayService

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
            }
        }
    }

    private fun toast(context:Context? , text: String) = Toast.makeText( context, text, Toast.LENGTH_LONG).show()



}