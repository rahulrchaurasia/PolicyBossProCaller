package com.policyboss.policybosscaller

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.policyboss.policybosscaller.databinding.DialogLoadingBinding


open class BaseFragment : Fragment() {

    private lateinit var dialog : Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = Dialog(requireActivity())

    }

    fun showDialog(msg: String = "Loading Please Wait!!"){

        if(!dialog.isShowing) {
            val dialogLoadingBinding = DialogLoadingBinding.inflate(layoutInflater)
            dialog.setContentView(dialogLoadingBinding.root)
            if (dialog.window != null) {

                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))

            }
            if(msg.isNotEmpty()){
                dialogLoadingBinding.txtMessage.text = msg

            }
            dialog.setCancelable(false)
            dialog.show()
        }
    }

    fun cancelDialog(){

        if(dialog.isShowing){

            dialog.dismiss()
        }


    }


    fun showSnackBar(view : View, strMessage: String){

        Snackbar.make(view, strMessage, Snackbar.LENGTH_SHORT).show()

    }

    fun showAlert(msg : String){

        val builder = AlertDialog.Builder(this.requireContext())
        builder.setTitle("Alert")
        builder.setMessage(msg)
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.ok) { dialog, which ->

        }


        builder.show()
    }

}