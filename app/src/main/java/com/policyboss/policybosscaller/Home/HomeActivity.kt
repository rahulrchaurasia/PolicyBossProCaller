package com.policyboss.policybosscaller.Home

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Transition
import com.example.policybosscaller.OverlayDemo.CallType
import com.policyboss.policybosscaller.BaseActivity
import com.policyboss.policybosscaller.OverlayDemo.CustomWIndow
import com.policyboss.policybosscaller.OverlayDemo.OverlayService
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.databinding.ActivityHomeBinding
import com.policyboss.policybosscaller.popup.PopUpAfterCallEndActivity

//http://horizon.policyboss.com:5000/posps/dsas/view/8774
class HomeActivity : BaseActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {

           OverlayService.startService(context = this@HomeActivity, callType = CallType.INCOMMING, phoneNumber = "00")

//            startActivity(Intent(this@HomeActivity, PopUpAfterCallEndActivity::class.java)
//                      .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//                      )





            // overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
          //  overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out)


        }

        binding.btnStop.setOnClickListener {

          //  OverlayService.stopService(context = this@HomeActivity)
          //  OverlayService.stopService1()
        }
    }
}