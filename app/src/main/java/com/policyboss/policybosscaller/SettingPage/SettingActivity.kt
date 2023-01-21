package com.policyboss.policybosscaller.SettingPage

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.policybosscaller.Utility.Constant
import com.example.policybosscaller.Utility.Utility
import com.policyboss.policybosscaller.BaseActivity
import com.policyboss.policybosscaller.Home.HomeActivity
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.databinding.ActivitySettingBinding

class SettingActivity : BaseActivity() , View.OnClickListener {
    private lateinit var binding : ActivitySettingBinding
    private var isReadPhoneState = false
    private var isReadCallLog = false
    // lateinit var CallLogContracts : ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setting()



    }

    override fun onResume() {
        super.onResume()

        init()
    }

    //region permission Launcher

    private val requestMultiplePermissions =     registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        permissions.entries.forEach {
            // Log.d("DEBUG", "RAHUL ${it.key} = ${it.value}")

            isReadPhoneState = permissions[Manifest.permission.READ_PHONE_STATE] ?: isReadPhoneState
            isReadCallLog = permissions[Manifest.permission.READ_CALL_LOG] ?: isReadCallLog

            if((isReadPhoneState) && (isReadCallLog)){

                setAllowButton()
                setPhoneStateUI()
                setCallLogUI()
            }
        }
    }

    private  val CallLogPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){

            isGranted ->
        // Do something if permission granted
        if (isGranted) {
            Log.i("DEBUG", "permission granted")

            setCallLogUI()
            verifyAllPermission()
        } else {
            Log.i("DEBUG", "permission denied")
            binding.tvCallLogInfo.setImageDrawable(ContextCompat.getDrawable(this@SettingActivity, R.drawable.circular_exclaimlayer))
        }
    }

    private  val PhoneStatePermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){

            isGranted ->
        // Do something if permission granted
        if (isGranted) {
            Log.i("DEBUG", "permission granted")
            // binding.tvCallLogInfo.setImageDrawable(ContextCompat.getDrawable(this@SettingActivity, R.drawable.circular_checklayer))
            setPhoneStateUI()
            verifyAllPermission()

        } else {
            Log.i("DEBUG", "permission denied")
            binding.tvPhoneStateInfo.setImageDrawable(ContextCompat.getDrawable(this@SettingActivity, R.drawable.circular_exclaimlayer))

        }
    }

    //endregion

    // region Check Permission
    fun hasReadPhonePermission() = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)

    fun hasCallLogPermission() = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)

    //endregion

    //region request Permission
    fun requestPhoneStatePermission() = PhoneStatePermissionLauncher.launch(  Manifest.permission.READ_PHONE_STATE)

    fun requestCallLogPermission() = CallLogPermissionLauncher.launch(  Manifest.permission.READ_CALL_LOG)


    private fun requestMultilplePermission(){

        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CALL_LOG
            )
        )
    }

    //endregion


    fun verifyAllPermission(){

        if (hasReadPhonePermission() == PackageManager.PERMISSION_GRANTED
            && hasCallLogPermission() == PackageManager.PERMISSION_GRANTED){

            setAllowButton()
        }
    }

    fun init(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {

                binding.btnAllowOverlay.text = "ALLOW"
            }else{
                binding.btnAllowOverlay.text = "Done"
            }
        }

    }

    fun setting(){

        binding.cardPhoneState.setOnClickListener(this)
        binding.cardCallLog.setOnClickListener(this)
        binding.cardOverlay.setOnClickListener(this)
        binding.btnContinue.setOnClickListener(this)
    }

    fun setAllowButton(){

        binding.btnContinue.background = ContextCompat.getDrawable(this@SettingActivity,R.drawable.round_rect_blue_shape)

        binding.btnContinue.setTextColor(ContextCompat.getColor(this@SettingActivity, R.color.white))

        binding.btnContinue.text = "CONTINUE"
        binding.btnContinue.textSize = 18f

    }

    fun setPhoneStateUI() {

        binding.tvPhoneStateInfo.setImageResource(R.drawable.circular_checklayer)
        binding.btnAllowPhoneState.text = "ALLOWED"

    }
    fun setCallLogUI() {


        binding.tvCallLogInfo.setImageDrawable(ContextCompat.getDrawable(this@SettingActivity, R.drawable.circular_checklayer))
        binding.btnAllowCallLog.text = "ALLOWED"
    }



    fun moveToOtherSettingPage(){

        this.finish()
        startActivity(Intent(this, OverlayPermissionActivity::class.java))

    }


    override fun onClick(view: View?) {

        when(view!!.id){

            binding.cardPhoneState.id -> {


                // PhoneStatePermissionLauncher.launch(Manifest.permission.READ_PHONE_STATE)

                requestPhoneStatePermission()
            }
            binding.cardCallLog.id -> {

                // CallLogPermissionLauncher.launch(Manifest.permission.READ_CALL_LOG)
                requestCallLogPermission()
            }
            binding.btnContinue.id -> {

                if (hasReadPhonePermission() == PackageManager.PERMISSION_GRANTED && hasCallLogPermission() == PackageManager.PERMISSION_GRANTED){

                    if(!Utility.isOverlayPermissionExist(this@SettingActivity)) {
                        startActivity(Intent(this, OverlayPermissionActivity::class.java))
                    }else{

                        startActivity(Intent(this, HomeActivity::class.java))
                    }


                }else{

                    requestMultilplePermission()

                }


            }
        }
    }



    // region Not in Used Call Log  and other way of req permission
    fun getPhoneNumber(){

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission check

            // Create obj of TelephonyManager and ask for current telephone service
            val telephonyManager = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
            val phoneNumber = telephonyManager.line1Number
            Log.d(Constant.TAG,"" + phoneNumber)
            return
        } else {
            // Ask for permission
            //  requestPermission()
        }

        //  phone_number!!.text = phoneNumber

    }

    private fun checkAndRequestPermissions(): Boolean {
        val readPhoneState =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)

        val readPhoneNumber =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS)

        val read_call_log =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)

        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (readPhoneState != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE)
        }
        if (readPhoneNumber != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_NUMBERS)
        }
        if (read_call_log != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CALL_LOG)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                Constant.REQUEST_ID_MULTIPLE_PERMISSIONS
            )
//            requestMultiplePermissions.launch(
//                arrayOf(
//                    Manifest.permission.READ_CONTACTS,
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                )
//            )
            return false
        }
        return true
    }

    @SuppressLint("Range")
    private fun getAllCallLogs() {
        // reading all data in descending order according to DATE
        val strOrder = CallLog.Calls.DATE + " DESC"
        val callUri = Uri.parse("content://call_log/calls")

        val cur: Cursor? = contentResolver.query(callUri, null, null, null, strOrder)
        // loop through cursor
        cur!!.moveToFirst()

        try{
            val callNumber: String =  cur.getString(cur!!.getColumnIndexOrThrow(CallLog.Calls.NUMBER))

            Log.d(Constant.TAG, callNumber)
        } catch (ex : java.lang.Exception){

            Log.d(Constant.TAG, ex.toString())
        }



    }

    //endregion

}