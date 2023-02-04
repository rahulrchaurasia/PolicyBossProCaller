package com.policyboss.policybosscaller.popup

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowManager
import com.example.policybosscaller.Prefrence.SharePrefernce
import com.example.policybosscaller.Utility.Constant
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.WebView.CommonWebViewActivity
import com.policyboss.policybosscaller.databinding.ActivityPopUpAfterCallEndBinding

class PopUpAfterCallEndActivity : AppCompatActivity() , OnClickListener {

    private lateinit var binding : ActivityPopUpAfterCallEndBinding

    private lateinit var sharePreferences: SharePrefernce
    override fun onCreate(savedInstanceState: Bundle?) {


        //For Look Screen
       // showWhenLockedAndTurnScreenOn()

        super.onCreate(savedInstanceState)
        binding = ActivityPopUpAfterCallEndBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(
            R.anim.slide_up_in, R.anim.slide_up_out)


        sharePreferences = SharePrefernce(this@PopUpAfterCallEndActivity)
        binding.lyParent.setOnClickListener(this)
        binding.lyPrivateCar.setOnClickListener(this)
        binding.lyTwoWheeler.setOnClickListener(this)
        binding.lyHealth.setOnClickListener(this)

         bindData()

        sharePreferences.clear()
    }


    fun getClassName() : String{

        return this@PopUpAfterCallEndActivity.getClassName().toString()
    }


    override fun onClick(view: View?) {

        when(view!!.id){

            binding.lyParent.id -> {


                this@PopUpAfterCallEndActivity.finish()


            }

            binding.lyPrivateCar.id -> {


                OpenWebView(Constant.PrivateCar)
                this@PopUpAfterCallEndActivity.finish()
            }
            binding.lyTwoWheeler.id -> {


                OpenWebView(Constant.TwoWheeler)
                this@PopUpAfterCallEndActivity.finish()
            }
            binding.lyHealth.id -> {


                OpenWebView(Constant.Health)
                this@PopUpAfterCallEndActivity.finish()
            }




        }
    }

    fun bindData(){

        binding.txtVehicleDtls.text = "Maruti, Wegon R, Petrol"
        binding.txtExpiryDate.text = "21-Mar-2023"
        binding.txtInsurer.text = "Bharti Axa"
        var strTemp = StringBuilder()
        strTemp.append(this.resources.getString(R.string.last_call))
        strTemp.append(sharePreferences.getCallType())
        strTemp.append(" " + this.resources.getString(R.string.from_call))
        strTemp.append(" ")
        strTemp.append(sharePreferences.getPhoneNumber().takeLast(10))

       // binding.txtMob.text = "${sharePreferences.getCallType()}: ${ sharePreferences.getPhoneNumber().takeLast(10)}"
        binding.txtMob.text = strTemp.toString()


    }


    private fun showWhenLockedAndTurnScreenOn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)
        } else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
        }
    }
    fun OpenWebView(type: String){

        var url = ""
        var name = ""
        when(type){

            Constant.PrivateCar ->{

                url = Constant.PrivateCarUrl
                name = "MOTOR INSURANCE"
            }
            Constant.TwoWheeler ->{

                url = Constant.TwoWheelerUrl
                name = "TWO WHEELER INSURANCE"
            }
            Constant.Health ->{

                url = Constant.HealthUrl
                name = "HEALTH INSURANCE"
            }
        }

        val dialogIntent = Intent(this@PopUpAfterCallEndActivity, CommonWebViewActivity::class.java)
            .putExtra("URL", url)
            .putExtra("NAME", name)
            .putExtra("TITLE", name);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

        startActivity(dialogIntent)

    }

    override fun onDestroy() {
        super.onDestroy()


    }


}