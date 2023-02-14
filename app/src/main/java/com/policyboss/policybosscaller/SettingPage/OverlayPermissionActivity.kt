package com.policyboss.policybosscaller.SettingPage

import android.content.Intent
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.policybosscaller.Prefrence.DataStoreManager
import com.example.policybosscaller.Utility.Constant
import com.example.policybosscaller.Utility.Utility
import com.example.policybosscaller.Utility.showSnackbar
import com.google.android.material.snackbar.Snackbar
import com.policyboss.policybosscaller.BaseActivity
import com.policyboss.policybosscaller.Home.HomeActivity
import com.policyboss.policybosscaller.login.LoginActivity
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.databinding.ActivityOverlayPermissionBinding
import com.policyboss.policybosscaller.popup.OverlayPopupPermissionActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OverlayPermissionActivity :  BaseActivity() , View.OnClickListener  {
    private lateinit var binding : com.policyboss.policybosscaller.databinding.ActivityOverlayPermissionBinding
    lateinit var layout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOverlayPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        layout = binding.root
        binding.cardOverlay.setOnClickListener(this)
        binding.cardBackground.setOnClickListener(this)
        binding.btnInfo.setOnClickListener(this)
        binding.btnAllowALL.setOnClickListener(this)

        verifyOverlayAndBackgroundPermission()


    }

    ////

    private  val overlayLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

        if(isOverlayPermissionExist()){

               updateOverlayUI()
                if(isBackgroundPermissionExist()){

                    updateAllowAll()
                    moveToDashBoard()

                }



        }else{
            layout.showSnackbar(
                R.string.permission_overlay_required,
                Snackbar.LENGTH_LONG,
                R.string.allow
            )
            {

                showOverlayPermission()
                Handler(Looper.getMainLooper()!!).postDelayed({

                    startActivity(Intent(this, OverlayPopupPermissionActivity::class.java)
                        .putExtra(Constant.IS_OVERLAYSCREEN,Constant.OVERLAY_DATA))

                },1200)
            }
        }


    }

    private  val backgroundLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

        if(result.resultCode ==  RESULT_OK) {

            //startActivity(Intent(this@OverlayPermissionActivity, HomeActivity::class.java))
            updateBackGroundUI()
            if(isOverlayPermissionExist()){

                updateOverlayUI()
                updateAllowAll()
                moveToDashBoard()

            }



        }else{

           // binding.root.showSnackbar("Required background permission to execute perfectly when app is in background")

            layout.showSnackbar(
                R.string.permission_background_required,
                Snackbar.LENGTH_LONG,
                R.string.allow
            )
            {

                backGroundBatteryOptimization()
            }

        }



    }

    fun moveToDashBoard(){

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {
                DataStoreManager(this@OverlayPermissionActivity).getFBAID().collect{

                    withContext(Dispatchers.Main){
                        if(it.length> 0 && !it.equals("0")){
                            startActivity(Intent(this@OverlayPermissionActivity, HomeActivity::class.java)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                            this@OverlayPermissionActivity.finish()
                        }else{
                            startActivity(Intent(this@OverlayPermissionActivity, LoginActivity::class.java)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                            this@OverlayPermissionActivity.finish()
                        }

                    }

                }
            }
        }
    }

    override fun onClick(viw: View?) {

        when(viw!!.id) {

            binding.cardOverlay.id -> {

                if(!isOverlayPermissionExist()){

                    showOverlayPermission()
                    Handler(Looper.getMainLooper()!!).postDelayed({

                        startActivity(Intent(this, OverlayPopupPermissionActivity::class.java)
                            .putExtra(Constant.IS_OVERLAYSCREEN,Constant.OVERLAY_DATA))

                    },1200)
                }
            }

            binding.cardBackground.id -> {

                backGroundBatteryOptimization()
            }

            binding.btnInfo.id -> {

               Utility.showCustom(this@OverlayPermissionActivity)


            }

            binding.btnAllowALL.id ->{

                if(!isOverlayPermissionExist()){

                    showOverlayPermission()
                    Handler(Looper.getMainLooper()!!).postDelayed({

                        startActivity(Intent(this, OverlayPopupPermissionActivity::class.java)
                            .putExtra(Constant.IS_OVERLAYSCREEN,Constant.OVERLAY_DATA))

                    },1200)
                }else if(!isBackgroundPermissionExist()){

                    backGroundBatteryOptimization()
                }else{

                    moveToDashBoard()
                }



            }


        }
    }

    override fun onResume() {
        super.onResume()


    }

    fun verifyOverlayAndBackgroundPermission(){

        if(isOverlayPermissionExist()){

            updateOverlayUI()
        }
        if(isBackgroundPermissionExist()){
            updateBackGroundUI()
        }
    }

    fun updateOverlayUI(){

        Handler(Looper.myLooper()!!).postDelayed({

            binding.tvOvelayInfo.setImageResource(R.drawable.circular_checklayer)
            binding.txtAllowOverlay.text = "ALLOWED"

            binding.btnAllowALL.text = "NEXT"

        },400)

      //  binding.btnCancel.visibility = View.GONE



    }

    fun updateBackGroundUI(){

        Handler(Looper.myLooper()!!).postDelayed({

            binding.tvBackgroundInfo.setImageResource(R.drawable.circular_checklayer)
            binding.txtAllowbackground.text = "ALLOWED"
            binding.btnAllowALL.text = "NEXT"
        },400)

      //  binding.btnCancel.visibility = View.GONE



    }

    fun updateAllowAll(){


        Handler(Looper.myLooper()!!).postDelayed({

            binding.btnAllowALL.background = ContextCompat.getDrawable(this@OverlayPermissionActivity, R.drawable.round_rect_blue_shape)

            binding.btnAllowALL.setTextColor(ContextCompat.getColor(this@OverlayPermissionActivity, R.color.white))
            binding.btnAllowALL.text = "CONTINUE"
            binding.btnAllowALL.textSize = 16f

        },400)


    }



    //region method to ask user to grant the Overlay permission
    fun showOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                // send user to the device settings
                val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                overlayLauncher.launch(myIntent)
               // startActivity(myIntent)
            }
        }
    }

    fun isOverlayPermissionExist() : Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this.applicationContext)) {

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
               // startActivity(intent)
               backgroundLauncher.launch(intent)
//                     Handler(Looper.myLooper()!!).postDelayed({
//                         startActivity(Intent(this, OverlayPopupPermissionActivity::class.java)
//                             .putExtra(Constant.IS_OVERLAYSCREEN,Constant.BACKGROUND_DATA))
//
//                     },200)
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