package com.policyboss.policybosscaller.popup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import com.example.policybosscaller.Utility.Constant
import com.policyboss.policybosscaller.databinding.ActivityOverlayPopupPermissionBinding

class OverlayPopupPermissionActivity : AppCompatActivity(){
    private lateinit var binding: ActivityOverlayPopupPermissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityOverlayPopupPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(intent.getStringExtra(Constant.IS_OVERLAYSCREEN) != null ){

            if(intent.hasExtra(Constant.IS_OVERLAYSCREEN)){

                if(intent.getStringExtra(Constant.IS_OVERLAYSCREEN).equals(Constant.OVERLAY_DATA)){
                    binding.lyOverlay.visibility = VISIBLE
                    binding.lyBackground.visibility = GONE
                }else  if(intent.getStringExtra(Constant.IS_OVERLAYSCREEN).equals(Constant.BACKGROUND_DATA)){

                    binding.lyBackground.visibility = VISIBLE
                    binding.lyOverlay.visibility = GONE
                }
            }

        }
       
        binding.lyParent.setOnClickListener {
            this.finish()
        }
    }




}