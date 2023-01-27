package com.policyboss.policybosscaller.SplashScreen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.example.policybosscaller.Utility.Utility
import com.policyboss.policybosscaller.Home.HomeActivity
import com.policyboss.policybosscaller.SettingPage.OverlayPermissionActivity
import com.policyboss.policybosscaller.SettingPage.SettingActivity
import com.policyboss.policybosscaller.databinding.ActivitySplashScreenBinding
import com.policyboss.policybosscaller.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    private val SPLASH_DISPLAY_LENGTH = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //user behaviour data collection in Async
        Handler(Looper.myLooper()!!).postDelayed({

            verifyAllPermission()

        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

    fun hasReadPhonePermission() = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)

    fun hasCallLogPermission() = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)



    fun verifyAllPermission(){

        if (hasReadPhonePermission() == PackageManager.PERMISSION_GRANTED
            && hasCallLogPermission() == PackageManager.PERMISSION_GRANTED){

            if(Utility.isOverlayPermissionExist(this@SplashScreenActivity) &&
                Utility.isBackgroundPermissionExist(this@SplashScreenActivity)){

                startActivity(Intent(this, LoginActivity::class.java))
            }else{

                startActivity(Intent(this, OverlayPermissionActivity::class.java))
            }


        }else{
            startActivity(Intent(this, SettingActivity::class.java))
        }
    }
}