package com.policyboss.policybosscaller.OverlayDemo

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.util.Log
import android.view.*
import com.example.policybosscaller.Prefrence.SharePrefernce
import com.policyboss.policybosscaller.WebView.CommonWebViewActivity
import com.policyboss.policybosscaller.databinding.PopupWindowBinding
import java.util.prefs.Preferences

/*****************************************************************************
 * Run When call is running (Hence we need Overlay Permission)
 * So over dialog is dispaly over the ring Call.
 * //https://www.geeksforgeeks.org/how-to-make-a-floating-window-application-in-android/
 * //Companion Object
 * //https://in-kotlin.com/design-patterns/singleton/#:~:text=singleton%20in%20Kotlin%3F-,Kotlin%20Singleton%20Class%20With%20Parameters,be%20instantiated%20by%20any%20client.
 *******************************************************************************/
// " ${context.resources.getString(R.string.from_call)}"
@Suppress("DEPRECATION")
class CustomWIndow {


    companion object{

        // private lateinit var context: Context

        private lateinit var mView: ViewGroup
        private lateinit var binding : PopupWindowBinding
        private  var mParams: WindowManager.LayoutParams
        private lateinit var mWindowManager: WindowManager
        private lateinit var layoutInflater: LayoutInflater
        //  private lateinit var mFrameLayout: FrameLayout

        init {
            mParams = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // set the layout parameters of the window
                WindowManager.LayoutParams( // Shrink the window to wrap the content rather
                    // than filling the screen
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,  // Display it on top of other application windows
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,  // For Overlay above Oreo
                    // Make the underlying application window visible
                    // through any transparent parts
                    //PixelFormat.TRANSLUCENT

                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                            or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                            or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                            or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                            or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD,
                    // through any transparent parts
                    PixelFormat.TRANSLUCENT
                )


            }
            else {
                WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,  // Display it on top of other application windows
                    WindowManager.LayoutParams.TYPE_PHONE,  // Don't let it grab the input focus
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,  // Make the underlying application window visible

                    WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                            or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                            or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                            or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                    // through any transparent parts
                    PixelFormat.TRANSLUCENT
                )
            }

           


        }

        fun openWindowPopUp( context: Context ,callType: String, PhoneNumber : String ) {

            mWindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager


            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = PopupWindowBinding.inflate(layoutInflater)
            mView = binding.root


            binding.txtMob.text = "${callType}: ${PhoneNumber.takeLast(10)}"

            binding.txtVehicleDtls.text = "Maruti, Wegon R, Petrol"
            binding.txtExpiryDate.text = "21-Mar-2023"
            binding.txtInsurer.text = "Bharti Axa"
            binding.windowClose.setOnClickListener { close(context) }



            binding.lyOther.setOnClickListener {
                // redirectToMain()
                Log.d("CALLER","Done")

                val dashBoardurl = "http://origin-cdnh.policyboss.com/fmweb/SyncContact/DashBoard.html?ss_id=121799&fba_id=92035&sub_fba_id=0&is_paid=yes&v=0307"


                val dialogIntent = Intent(context, CommonWebViewActivity::class.java)
                    .putExtra("URL", dashBoardurl)
                    .putExtra("NAME", "DashBoard")
                    .putExtra("TITLE", "DashBoard");
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)


                context.startActivity(dialogIntent)


                close(context)



            }

            //region Touch Listener
            mView.setOnTouchListener(object : View.OnTouchListener {
                val floatWindowLayoutUpdateParam: WindowManager.LayoutParams = mParams
                var x = 0.0
                var y = 0.0
                var px = 0.0
                var py = 0.0
                override fun onTouch(v: View?, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            x = floatWindowLayoutUpdateParam.x.toDouble()
                            y = floatWindowLayoutUpdateParam.y.toDouble()

                            // returns the original raw X
                            // coordinate of this event
                            px = event.rawX.toDouble()

                            // returns the original raw Y
                            // coordinate of this event
                            py = event.rawY.toDouble()
                        }
                        MotionEvent.ACTION_MOVE -> {
                            floatWindowLayoutUpdateParam.x = (x + event.rawX - px).toInt()
                            floatWindowLayoutUpdateParam.y = (y + event.rawY - py).toInt()

                            // updated parameter is applied to the WindowManager
                            mWindowManager.updateViewLayout(mView, floatWindowLayoutUpdateParam)
                        }
                    }
                    return false
                }
            })

            //endregion

            try {
                // check if the view is already
                // inflated or present in the window
                if (mView!!.windowToken == null) {
                    if (mView!!.parent == null) {
                        mWindowManager!!.addView(mView, mParams)

                    }
                }
            } catch (e: Exception) {
                Log.d("Error1", e.toString())


            }

        }

        fun verifyExsist() : Boolean{

            var bln = false

            try{


                if (mView.getParent() != null) {
                   bln = true
                }else{
                    bln = false
                }

            }catch (ex :Exception){
                bln = false

            }
            return bln

        }

        fun close(context: Context ) {
            try {

//                if(isCalledByService){
//                    //close the Service
//                    OverlayService.stopService(context!!)
//
//                }

                // remove the view from the window
                (context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager).removeView(mView)

                // invalidate the view
                mView!!.invalidate()

                // remove all views
                //  (mView!!.parent as ViewGroup).removeAllViews()    // not running

                //close the Service
                // OverlayService.stopService(context)

                // the above steps are necessary when you are adding and removing
                // the view simultaneously, it might give some exceptions
            } catch (e: Exception) {

                Log.d("Error2", e.toString())
            }
        }

//
//        private fun showWhenLockedAndTurnScreenOn(context: Context) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
//                setShowWhenLocked(true)
//                setTurnScreenOn(true)
//            } else {
//                window.addFlags(
//                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//                            or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
//                )
//            }
//        }

    }

}