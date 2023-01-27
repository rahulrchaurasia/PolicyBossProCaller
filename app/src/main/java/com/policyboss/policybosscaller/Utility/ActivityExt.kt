package com.policyboss.policybosscaller.Utility

import android.app.Activity
import android.widget.Toast
import com.policyboss.policybosscaller.databinding.ActivityTestBinding

class ActivityExt {


    fun Activity.showDialog(){

        Toast.makeText(applicationContext, "text", Toast.LENGTH_SHORT).show()
    }
}