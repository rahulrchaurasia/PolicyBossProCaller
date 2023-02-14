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
import android.view.*
import androidx.core.app.NotificationCompat
import com.example.policybosscaller.Prefrence.SharePrefernce
import com.example.policybosscaller.Utility.Constant
import com.policyboss.policybosscaller.Home.HomeActivity
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.data.model.CallType
import com.policyboss.policybosscaller.popup.OverlayPopupPermissionActivity
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

        private var notificationManager : NotificationManager? = null
        

        fun  getServiceContext(mcontext: Context) : Context {

            if(context == null){
                context = mcontext

            }
            if(notificationManager == null){
                notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            }

            return context as Context
        }

       // @SuppressLint("SuspiciousIndentation")
        fun startServiceAfterBoot(context: Context) {

        try {


            val startIntent = Intent(context, OverlayService::class.java)
            startIntent.setAction(Constant.SERVICE_AFTER_BOOT)


            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                Handler(Looper.getMainLooper()).post{

                    // ContextCompat.startForegroundService(context,startIntent)

                    context.startForegroundService(startIntent)
                }


            } else {


                Handler(Looper.getMainLooper()).post{

                    // context.startService(startIntent)
                    context.startService(startIntent)
                }
            }

        }
        catch (ex : Exception){ }


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

        fun stopServiceWithoutEndPopuCall(){


            context?.let {

                val startIntent = Intent(context, OverlayService::class.java)
                startIntent.setAction(Constant.SERVICE_STOP_INCOMING_ANSWERED)
                it.startService(startIntent)
            }


        }

        fun startService(context: Context, callType: CallType, phoneNumber: String? = "") {

            try {

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
                startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)


                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                    Handler(Looper.getMainLooper()).post{

                       // ContextCompat.startForegroundService(context,startIntent)

                        context.startForegroundService(startIntent)
                    }


                } else {


                    Handler(Looper.getMainLooper()).post{

                       // context.startService(startIntent)
                        context.startService(startIntent)
                    }
                }


            }
            catch (ex : Exception){ }


        }




        private fun createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val serviceChannel = NotificationChannel(
                                    Constant.CHANNEL_ID,
                                    Constant.CHANNEL_NAME,
                                    NotificationManager.IMPORTANCE_DEFAULT)
                //val manager = getSystemService(NotificationManager::class.java)
                serviceChannel.lockscreenVisibility =
                    Notification.VISIBILITY_PUBLIC
                notificationManager!!.createNotificationChannel(serviceChannel)
            }
        }

        fun isServiceRunning(context: Context ): Boolean {
            val activityManager =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val services: List<ActivityManager.RunningServiceInfo> = activityManager.getRunningServices(Int.MAX_VALUE)
            for (runningServiceInfo in services) {
                if (runningServiceInfo.service.className.equals(OverlayService::class.java.name)) {
                    return true
                }
            }
            return false
        }

        fun isEndPopUpDialogExist() : Boolean{

            var bln = false
            try {
                val result = context!!.applicationContext.getSystemService(ACTIVITY_SERVICE) as ActivityManager
                val services: List<ActivityManager.RunningTaskInfo> = result
                    .getRunningTasks(Int.MAX_VALUE)


                if(services != null){

                    Log.d(Constant.TAG,"TOP ACTIVITY" + services[0].topActivity.toString())
                    if ((services[0].topActivity.toString().contains(Constant.EndActivity))){

                        bln = true
                        Log.d(Constant.TAG," TOP ACTIVITY is Matched " + services[0].topActivity.toString())
                       // val popUpEndActivity : PopUpAfterCallEndActivity = services[0].topActivity as PopUpAfterCallEndActivity
                     //   popUpEndActivity.finish()

                        ( services[0].topActivity as PopUpAfterCallEndActivity).finish()

                    } else {   bln = false}
                }

            }catch (ex : Exception){

                Log.d(Constant.TAG, ex.toString())
            }
            return bln
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

                        SharePrefernce(context!!).savePhoneCallType(callType,phoneNumber)
                        if(  SharePrefernce(context!!).isOverlayPopup())
                        {
                            if(!CustomWIndow.verifyExsist() ) {


                                // region Start Service
                                // Create Forground Notification
                                //isEndPopUpDialogExist()
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    createNotificationChannel()
                                    showForeGroundNotification(callType, phoneNumber)

                                } else {

                                    showForeGroundNotification(callType, phoneNumber)
                                }

                                CustomWIndow.openWindowPopUp(
                                    this.applicationContext,
                                    callType = callType,
                                    PhoneNumber = phoneNumber
                                )
                            }else{}
                        } else { }


                        //region Comment
//                        verifyExsist(this.applicationContext)
//                        Handler(Looper.getMainLooper()).postDelayed({
//
//                            openWindowPopUp(
//                                this.applicationContext,
//                                callType = callType,
//                                PhoneNumber = phoneNumber
//                            )
//
//
//                        }, 200)
                        //endregion

                       // isEndPopUpDialogExist()

                    }

                    intent.action.equals(Constant.SERVICE_AFTER_BOOT) -> {
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            createNotificationChannel()
                            showForeGroundNotification(callType, phoneNumber)

                        }else{
                            showForeGroundNotification(callType, phoneNumber)
                        }
                    }

                    intent.action.equals(Constant.SERVICE_STOP) -> {

                        //region Stop Service
                        Log.d(Constant.TAG, "Service STOP")

                        //

                        //stopSelf()       // Stop the Service

                        CustomWIndow.close(context!!)    // Window CustomPopup is Closed


//                        Handler(Looper.getMainLooper()).post{          //  Dialog Open After Custom Dialog
//
//
//                        }

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

                        },700)

                        //endregion
                    }

                    intent.action.equals(Constant.SERVICE_STOP_INCOMING_ANSWERED) -> {


                        CustomWIndow.close(context!!)    // Window CustomPopup is Closed When User recieved the call

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

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)


    }

    override fun onDestroy() {
        super.onDestroy()
       // stopSelf()
    }





    //region Forground Notification

    private fun showForeGroundNotification(callType: String, phoneNumber : String){

        val notificationIntent = Intent(this, HomeActivity::class.java)

//        val flag = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            PendingIntent.FLAG_IMMUTABLE
//        }else {0 }
//        val pendingIntent = PendingIntent.getActivity(
//            this,
//            0, notificationIntent, flag
//        )

        //.setContentText("${callType}: ${phoneNumber}")

        val notification = NotificationCompat.Builder(this, Constant.CHANNEL_ID)
            .setContentTitle("PolicyBoss Pro Caller is Enable")
            .setSmallIcon(R.drawable.ic_call_24)
           // .setContentIntent(pendingIntent)
           // .setFullScreenIntent(pendingIntent,true)
            .setColor(Color.GREEN)
            .build()
       // startForeground(Math.round(Math.random()*1000).toInt(), notification)


        startForeground(7, notification)
    }



    //endregion






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





    //endregion




}