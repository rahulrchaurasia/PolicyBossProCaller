package com.policyboss.policybosscaller.OverlayDemo

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.provider.CallLog
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.policybosscaller.OverlayDemo.CallType
import com.example.policybosscaller.OverlayDemo.WindowOverlay
import com.example.policybosscaller.Prefrence.ApplicationPersistance
import com.example.policybosscaller.Utility.Constant
import com.example.policybosscaller.Utility.NotifyService
import com.policyboss.policybosscaller.Home.HomeActivity
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.popup.PopUpAfterCallEndActivity


/******************* Overlay Demo ******************************************/
//https://www.geeksforgeeks.org/how-to-draw-over-other-apps-in-android/    // VVIMP
// https://www.youtube.com/watch?v=UwnhGkahoao
//https://www.youtube.com/watch?v=BXwDM5VVuKA
/*******************************************************************************/
//   http://horizon.policyboss.com:5000/posps/dsas/view/8774

/****************************************************************************************************
  Task DESCRIPTION :-  We Start Forground Service , HENCE we can come from background to forground  and do the Task.
    once Task is executed completed  we stop the Forground Service. So default Forground Service Notification is vanish.
 *************************************************************************************************************************************/


@Suppress("DEPRECATION")
class OverlayService : Service()  {

   // private val CHANNEL_ID = "com.example.rahuldemoapp.notifications441"

    companion object {


        private var context: Context? = null

        fun  getServiceContext(mcontext: Context) : Context {

            if(context == null){
                context = mcontext
            }
            return context as Context
        }
       // @SuppressLint("SuspiciousIndentation")
        fun startService(context: Context, callType: CallType, phoneNumber: String? = "") {



           var strCALLTYPE = ""

           when(callType){
               CallType.INCOMMING ->{
                   strCALLTYPE =
                          context.resources.getString(R.string.incomming_call)


               }
               CallType.OUTGOING -> {
                   strCALLTYPE = context.resources.getString(R.string.outgoing_call)

               }
           }

           val startIntent = Intent(context, OverlayService::class.java)
           startIntent.setAction(Constant.SERVICE_START)
           startIntent.putExtra(Constant.CALL_TYPE,strCALLTYPE)
           startIntent.putExtra(Constant.Phone_NUMBER,phoneNumber)

           if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {


               ContextCompat.startForegroundService(context,startIntent)

           } else {

               context.startService(startIntent)
           }


        }


        fun stopService(context: Context){

            val stopIntent = Intent(context, OverlayService::class.java)
            context.stopService(stopIntent)


        }

        fun stopService(){


          context?.let {

              val startIntent = Intent(context, OverlayService::class.java)
              startIntent.setAction(Constant.SERVICE_STOP)
              it.startService(startIntent)
          }


        }




    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }


    override fun onCreate() {
        super.onCreate()
        // create the custom or default notification
        // based on the android version


        // create an instance of Window class
        // and display the content on screen


    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
       // return super.onStartCommand(intent, flags, startId)
        val callType = intent?.getStringExtra(Constant.CALL_TYPE)?:""
        val phoneNumber = intent?.getStringExtra(Constant.Phone_NUMBER) ?:""

       // val phoneNumber =  getCallLogs()

        //region Service Action
        if(intent?.action != null){

            intent?.action.let {

                when {

                    intent.action.equals(Constant.SERVICE_START) -> {
                        // region Start Service
                        // Create Forground Notification
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            createNotificationChannel()
                            showForeGroundNotification(callType, phoneNumber)

                        } else {


                        }

                        ApplicationPersistance(context!!).savePhoneCallType(callType,phoneNumber)

                        CustomWIndow.verifyExsist(this.applicationContext)
                        Handler(Looper.getMainLooper()).postDelayed({

                            CustomWIndow.openWindowPopUp(
                                this.applicationContext,
                                callType = callType,
                                PhoneNumber = phoneNumber
                            )


                        }, 200)

                       // isEndPopUpDialogExist()



                        //endregion
                    }
                    intent.action.equals(Constant.SERVICE_STOP) -> {

                        //region Stop Service
                        Log.d(Constant.TAG, "Service STOP")

                        //

                        CustomWIndow.close(this.applicationContext)    // Window Popup is Closed

                        Handler(Looper.getMainLooper()).postDelayed({

                            startActivity(
                                Intent(
                                    this.applicationContext,
                                    PopUpAfterCallEndActivity::class.java
                                )
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                            )


                            stopSelf()      // Forground Service is closed

                        }, 400)

                        //endregion
                    }
                    else -> {
                        Log.d(Constant.TAG, "No Service Triggered")
                    }
                }


            }
        }

        //endregion


        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
    }




    //region Forground Notification

    private fun showForeGroundNotification(callType: String, phoneNumber : String){

        val notificationIntent = Intent(this, HomeActivity::class.java)

        val flag = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            PendingIntent.FLAG_IMMUTABLE
        }else {0 }
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, flag
        )

        //.setContentText("${callType}: ${phoneNumber}")

        val notification = NotificationCompat.Builder(this, Constant.CHANNEL_ID)
            .setContentTitle("PolicyBoss Pro Caller is Enable")
            .setSmallIcon(R.drawable.ic_call_24)
            .setContentIntent(pendingIntent)
           // .setFullScreenIntent(pendingIntent,true)
            .setColor(Color.GREEN)
            .build()
        startForeground(Math.round(Math.random()*1000).toInt(), notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(Constant.CHANNEL_ID, Constant.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

    //endregion


    fun isEndPopUpDialogExist(){

        try {
            val result = this.getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val services: List<ActivityManager.RunningTaskInfo> = result
                .getRunningTasks(Int.MAX_VALUE)


            if(services != null){

                Log.d(Constant.TAG,"TOP ACTIVITY" + services[0].topActivity.toString())
                if ((services[0].topActivity.toString().contains(Constant.EndActivity))){

                    Log.d(Constant.TAG," TOP ACTIVITY is Matched " + services[0].topActivity.toString())
                    val popUpEndActivity = services[0].topActivity as PopUpAfterCallEndActivity
                    popUpEndActivity.finish()

                } else { }
            }
            else{ }
        }catch (ex :java.lang.Exception){

        }

    }



    //region Not in Used
    @SuppressLint("Range")
    private fun getCallLogs() : String {
        var callNumber: String = ""
        // reading all data in descending order according to DATE
        val strOrder = CallLog.Calls.DATE + " DESC"
        val callUri = Uri.parse("content://call_log/calls")

        val cur: Cursor? = contentResolver.query(callUri, null, null, null, strOrder)
        // loop through cursor
        cur!!.moveToFirst()

        try{
             callNumber =  cur.getString(cur!!.getColumnIndexOrThrow(CallLog.Calls.NUMBER))

           // Log.d(Constant.TAG, callNumber)
        } catch (ex : java.lang.Exception){

            Log.d(Constant.TAG, ex.toString())
        }

        return  callNumber

    }

    fun isServiceRunning(context: Context ): Boolean {
        val activityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services: List<ActivityManager.RunningServiceInfo> = activityManager.getRunningServices(Int.MAX_VALUE)
        for (runningServiceInfo in services) {
            if (runningServiceInfo.service.className.equals(OverlayService.Companion::class.java.name)) {
                return true
            }
        }
        return false
    }



    //endregion




}