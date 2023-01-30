package com.policyboss.policybosscaller.Utility

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import com.policyboss.policybosscaller.databinding.ActivityTestBinding
import com.policyboss.policybosscaller.databinding.CustomBackgroundBinding
import com.policyboss.policybosscaller.databinding.DialogLoadingBinding

class ActivityExt {


    fun Activity.showDialog(){

        Toast.makeText(applicationContext, "text", Toast.LENGTH_SHORT).show()
    }



}