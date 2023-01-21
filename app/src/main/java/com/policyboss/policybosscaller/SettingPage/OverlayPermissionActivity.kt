package com.policyboss.policybosscaller.SettingPage

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.policybosscaller.Utility.Constant
import com.policyboss.policybosscaller.BaseActivity
import com.policyboss.policybosscaller.Home.HomeActivity
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.databinding.ActivityOverlayPermissionBinding
import com.policyboss.policybosscaller.popup.OverlayPopupPermissionActivity


class OverlayPermissionActivity :  BaseActivity() , View.OnClickListener  {
    private lateinit var binding : com.policyboss.policybosscaller.databinding.ActivityOverlayPermissionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOverlayPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardOverlay.setOnClickListener(this)
        binding.cardBackground.setOnClickListener(this)
        binding.btnAllowOverlay.setOnClickListener(this)

    }

    override fun onClick(viw: View?) {

        when(viw!!.id) {

            binding.cardOverlay.id -> {

                if(!isOverlayPermissionExist()){

                    showOverlayPermission()
                    Handler(Looper.myLooper()!!).postDelayed({

                        startActivity(Intent(this, OverlayPopupPermissionActivity::class.java)
                            .putExtra(Constant.IS_OVERLAYSCREEN,Constant.OVERLAY_DATA))

                    },200)
                }
            }

            binding.cardBackground.id -> {

                backGroundBatteryOptimization()
            }

           binding.btnAllowOverlay.id-> {

                if(!isOverlayPermissionExist()){

                    showOverlayPermission()
                    Handler(Looper.myLooper()!!).postDelayed({

                        startActivity(Intent(this, OverlayPopupPermissionActivity::class.java))

                    },200)

                }else{

                    startActivity(Intent(this, HomeActivity::class.java))

                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(isBackgroundPermissionExist()){
            updateBackGroundUI()
        }
        if(isOverlayPermissionExist()){
            updateUI()
        }



    }

    fun updateUI(){

        Handler(Looper.myLooper()!!).postDelayed({



            binding.btnAllowOverlay.background = ContextCompat.getDrawable(this@OverlayPermissionActivity, R.drawable.round_rect_blue_shape)

            binding.btnAllowOverlay.setTextColor(ContextCompat.getColor(this@OverlayPermissionActivity, R.color.white))
            binding.btnAllowOverlay.text = "CONTINUE"
            binding.btnAllowOverlay.textSize = 16f


            binding.tvOvelayInfo.setImageResource(R.drawable.circular_checklayer)

        },400)

      //  binding.btnCancel.visibility = View.GONE



    }

    fun updateBackGroundUI(){

        Handler(Looper.myLooper()!!).postDelayed({

            binding.tvBackgroundInfo.setImageResource(R.drawable.circular_checklayer)
        },400)

      //  binding.btnCancel.visibility = View.GONE



    }

    //region method to ask user to grant the Overlay permission
    fun showOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                // send user to the device settings
                val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                startActivity(myIntent)
            }
        }
    }

    fun isOverlayPermissionExist() : Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {

                return true
            }else{

                return false
            }
        }else{

            return true
        }

    }

    fun isBackgroundPermissionExist() : Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val packageName = packageName
            val pm = getSystemService(POWER_SERVICE) as PowerManager
            if (pm.isIgnoringBatteryOptimizations(packageName)) {

                return true
            }else{

                return false
            }
        }else{

            return true
        }

    }


    fun backGroundBatteryOptimization(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent()
            val packageName = packageName
            val pm = getSystemService(POWER_SERVICE) as PowerManager
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                intent.data = Uri.parse("package:$packageName")
                startActivity(intent)
                     Handler(Looper.myLooper()!!).postDelayed({
                         startActivity(Intent(this, OverlayPopupPermissionActivity::class.java)
                             .putExtra(Constant.IS_OVERLAYSCREEN,Constant.BACKGROUND_DATA))

                     },200)
            }else{

               updateBackGroundUI()
            }
        }
    }
    fun openAppSetting(){

        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, Constant.REQUEST_PERMISSION_SETTING)
    }

    // check for permission again when user grants it from
    // the device settings, and start the service

    //endregion
}