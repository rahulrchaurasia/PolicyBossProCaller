package com.example.policybosscaller.OverlayDemo

import android.content.Context
import android.content.Context.POWER_SERVICE
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.PixelFormat
import android.os.Build
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.WebView.CommonWebViewActivity


/*****************************************************************************
 * Run When call is running (Hence we need Overlay Permission)
 * So over dialog is dispaly over the ring Call.
 * //https://www.geeksforgeeks.org/how-to-make-a-floating-window-application-in-android/
 * //Companion Object
 * //https://in-kotlin.com/design-patterns/singleton/#:~:text=singleton%20in%20Kotlin%3F-,Kotlin%20Singleton%20Class%20With%20Parameters,be%20instantiated%20by%20any%20client.
 *******************************************************************************/
// " ${context.resources.getString(R.string.from_call)}"
@Suppress("DEPRECATION")
class WindowOverlay {

   // private lateinit var context: Context


   // private val floatWindowLayoutParam: WindowManager.LayoutParams? = null

    companion object{

       // private lateinit var context: Context

        private lateinit var mView: ViewGroup
        private lateinit var mParams: WindowManager.LayoutParams
        private lateinit var mWindowManager: WindowManager
        private lateinit var layoutInflater: LayoutInflater
      //  private lateinit var mFrameLayout: FrameLayout



        fun openWindowPopUp(context: Context, callType: String, PhoneNumber : String ) {

           
            //   val inflater = getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE)


                mParams = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // set the layout parameters of the window
                    WindowManager.LayoutParams( // Shrink the window to wrap the content rather
                        // than filling the screen
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,  // Display it on top of other application windows
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,  // For Overlay above Oreo
                        // Don't let it grab the input focus
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,  // Make the underlying application window visible
                        // through any transparent parts
                        //PixelFormat.TRANSLUCENT

                        WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                                or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                                or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                                or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
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


                mWindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

              //  mFrameLayout =  FrameLayout(context)
                // getting a LayoutInflater
                layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                // inflating the view with the custom layout we created
                //  mView = layoutInflater.inflate(R.layout.popup_window, mFrameLayout) as ViewGroup
                mView = layoutInflater.inflate(R.layout.popup_window, null) as ViewGroup
                // set onClickListener on the remove button, which removes
                // the view from the window
                val textMob : TextView = mView!!.findViewById(R.id.txtMob)

                textMob.text = "${callType}: ${PhoneNumber.takeLast(10)}"

                val txtVehicleDtls : TextView = mView!!.findViewById(R.id.txtVehicleDtls)
                val txtExpiryDate : TextView = mView!!.findViewById(R.id.txtExpiryDate)
                val txtInsurer : TextView = mView!!.findViewById(R.id.txtInsurer)

                txtVehicleDtls.text = "Maruti, Wegon R, Petrol"
                 txtExpiryDate.text = "21-Mar-2023"
                 txtInsurer.text = "Bharti Axa"
                mView!!.findViewById<View>(R.id.window_close).setOnClickListener { close(context) }



                mView!!.findViewById<View>(R.id.lyOther).setOnClickListener {
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




                ////

                // Another feature of the floating window is, the window is movable.
                // The window can be moved at any position on the screen.

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

                // Define the position of the window within the screen



            try {
                // check if the view is already
                // inflated or present in the window
                if (mView!!.windowToken == null) {
                    if (mView!!.parent == null) {
                      //  mParams.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        mWindowManager!!.addView(mView, mParams)

                    }
                }
            } catch (e: Exception) {
                Log.d("Error1", e.toString())


            }

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



    fun open() {


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






}