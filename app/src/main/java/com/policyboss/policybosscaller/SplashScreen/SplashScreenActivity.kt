package com.policyboss.policybosscaller.SplashScreen

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.policybosscaller.Prefrence.DataStoreManager
import com.example.policybosscaller.Utility.Constant
import com.example.policybosscaller.Utility.Utility
import com.policyboss.policybosscaller.Home.HomeActivity
import com.policyboss.policybosscaller.SettingPage.OverlayPermissionActivity
import com.policyboss.policybosscaller.SettingPage.SettingActivity
import com.policyboss.policybosscaller.databinding.ActivitySplashScreenBinding
import com.policyboss.policybosscaller.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    private val SPLASH_DISPLAY_LENGTH = 1000

    //region commneted broadcastReceiver
//      val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
//        // we will receive data updates in onReceive method.
//        override fun onReceive(context: Context?, intent: Intent) {
//            // Get extra data included in the Intent
//            if (intent.action != null) {
//                if (intent.action.equals(Constant.APP_INSTALL_ACTION, ignoreCase = true)) {
//
//                    val Data = intent.getStringExtra(Constant.APP_INSTALL_DATA)
//                    Log.d("AppInstallReceiver", "Caller is installed: "+ Data)
//                }
//
//            }
//
//        }
//    }
//
    //endregion


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(0, 0)

        //user behaviour data collection in Async
        Handler(Looper.myLooper()!!).postDelayed({

            verifyAllPermission()

        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

    fun hasReadPhonePermission() = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)

    fun hasCallLogPermission() = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)




    override fun onPause() {
        super.onPause()
       // LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        //LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }

    fun verifyAllPermission(){

        if (hasReadPhonePermission() == PackageManager.PERMISSION_GRANTED
            && hasCallLogPermission() == PackageManager.PERMISSION_GRANTED){

            if(Utility.isOverlayPermissionExist(this@SplashScreenActivity) &&
                Utility.isBackgroundPermissionExist(this@SplashScreenActivity)){


                lifecycleScope.launch {

                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        DataStoreManager(this@SplashScreenActivity).getFBAID().collect{

                            withContext(Dispatchers.Main){
                                if(it.length> 0 && !it.equals("0")){
                                    startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
                                    this@SplashScreenActivity.finish()
                                }else{
                                    startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java)
                                        .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
                                    this@SplashScreenActivity.finish()
                                }

                            }

                        }
                    }
                }



            }else{

                startActivity(Intent(this, OverlayPermissionActivity::class.java))
                this@SplashScreenActivity.finish()
            }


        }else{
            startActivity(Intent(this, SettingActivity::class.java))
            this@SplashScreenActivity.finish()
        }
    }
}