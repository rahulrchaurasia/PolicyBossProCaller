package com.policyboss.policybosscaller.BroadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.policybosscaller.Utility.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AppInstallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val packageName: String = intent?.getData()?.getSchemeSpecificPart() ?: ""

       if(intent?.getAction() != null){

           if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
               // App installed

               Log.d("AppInstallReceiver", "App installed: $packageName")


           } else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
               // App uninstalled

               Log.d("AppInstallReceiver", "App uninstalled: $packageName")

           }
       }


    }
}