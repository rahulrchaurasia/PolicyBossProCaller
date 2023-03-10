package com.policyboss.policybosscaller

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.policyboss.policybosscaller.databinding.ActivityBaseBinding
import com.policyboss.policybosscaller.databinding.DialogLoadingBinding

open class BaseActivity : AppCompatActivity() {
    private lateinit var bindingMain : ActivityBaseBinding

    private lateinit var dialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        dialog = Dialog(this)


    }

    open fun toast( text: String) = Toast.makeText( this, text, Toast.LENGTH_SHORT).show()





//    fun showAlert(msg : String){
//
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Alert")
//        builder.setMessage(msg)
//        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
//
//        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
//
//        }
//
//
//        builder.show()
//    }


    open fun showSnackBar(view : View, strMessage: String){

        Snackbar.make(view, strMessage, Snackbar.LENGTH_SHORT).show()

    }

    open fun showToast(strMessage: String){
        Toast.makeText(this,strMessage, Toast.LENGTH_SHORT).show()
    }





}