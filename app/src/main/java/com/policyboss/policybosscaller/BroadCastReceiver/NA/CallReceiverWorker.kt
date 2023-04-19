package com.policyboss.policybosscaller.BroadCastReceiver.NA


import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.policybosscaller.Prefrence.SharePrefernce
import com.example.policybosscaller.Utility.Constant
import com.example.policybosscaller.Utility.NotifyService
import com.policyboss.policybosscaller.OverlayDemo.CallWorkManager
import kotlinx.coroutines.*
import java.util.*

//https://stackoverflow.com/questions/5990590/how-to-detect-phone-call-broadcast-receiver-in-android
//https://gist.github.com/mrfoh/705b3ddeca57ac423ae2343cfd7acdb5

@Suppress("DEPRECATION")
class CallReceiverWorker  : BroadcastReceiver() {

   // private var lastState = TelephonyManager.CALL_STATE_IDLE
    private  var lastState : Int? = null

    private var callStartTime: Date? = null
    private var isInComingCall : Boolean? = null
    private var savedNumber: String? = null
    var state = 0



    @SuppressWarnings
    override fun onReceive(context: Context?, intent: Intent?) {


        isInComingCall = SharePrefernce(context!!).readIsCallInComming()
        // Incoming call
        val stateStr: String = intent!!.getExtras()!!.getString(TelephonyManager.EXTRA_STATE)!!

        //getPhoneNumber(context)
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = intent.getExtras()?.getString("android.intent.extra.PHONE_NUMBER") ?: ""
           // toast(context, "OutGoing Number" + savedNumber)
           // Log.d(Constant.TAG, " phone no : Outgoing " + savedNumber)

        }

        if (!intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER).isNullOrEmpty()) {
            savedNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            // toast(context, "EXTRA_INCOMING_NUMBER" + number)
          //  Log.d(Constant.TAG, " phone no : InComming " + savedNumber)
        }





        //  ******************* LogiC ******************************************

        if(!savedNumber.isNullOrEmpty()){
            //if (!stateStr.equals(mLastState)) {
              //  mLastState = stateStr;
                //Log.e(Constant.TAG, stateStr)

             if (stateStr == TelephonyManager.EXTRA_STATE_IDLE) {
                state = TelephonyManager.CALL_STATE_IDLE

                // OverlayService.stopService()

            }
             else  if (stateStr == TelephonyManager.EXTRA_STATE_RINGING) {
                    state = TelephonyManager.CALL_STATE_RINGING

                }

             else   if (stateStr == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                        state = TelephonyManager.CALL_STATE_OFFHOOK

                // OverlayService.startService(OverlayService.getServiceContext(mcontext = context!!),callType = CallType.OUTGOING , phoneNumber = savedNumber )

             }

               onCallStateChanged(context, state, savedNumber)


            //}
        }

    }



    private fun toast(context:Context? , text: String) = Toast.makeText( context, text, Toast.LENGTH_SHORT).show()


    //Derived classes should override these to respond to specific events of interest

    protected fun onIncomingCallStarted(context: Context,phoneNumber : String?) {
        NotifyService.callNotification(mcontext = context!!,callType = Constant.StartCall, phoneNumber = phoneNumber)


            Log.d(Constant.TAG,"onIncomingCallStarted"+ phoneNumber)
            // Only For Ringing

        WorkManager.getInstance(context).cancelAllWorkByTag(Constant.CALL_RECEIVER)

        WorkManager.getInstance(context.applicationContext)
            .beginUniqueWork(
                Constant.CALL_RECEIVER, ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(CallWorkManager::class.java)
            ).enqueue()


    }
    protected fun onOutgoingCallStarted(context: Context,phoneNumber : String?) {
        NotifyService.callNotification(mcontext = context!!,callType = Constant.StartCall, phoneNumber = phoneNumber)


        // Create Forground Notification


//         Log.d(Constant.TAG,"onOutgoingCallStarted"+ phoneNumber)
//            //  Outgoing call
//            OverlayService.startService(
//                context = OverlayService.getServiceContext(context.applicationContext),
//                callType = CallType.OUTGOING ,
//                phoneNumber = savedNumber )



//        val workManager: WorkManager = WorkManager.getInstance(context.applicationContext)
//
//        val parallelWorks: MutableList<OneTimeWorkRequest> = mutableListOf<OneTimeWorkRequest>()
//
//        parallelWorks.add(callLogWorkRequest)
//        workManager.beginWith(parallelWorks)
//            .enqueue()



        WorkManager.getInstance(context).cancelAllWorkByTag(Constant.CALL_RECEIVER)

        val callLogWorkRequest: OneTimeWorkRequest =
            OneTimeWorkRequest.Builder(CallWorkManager::class.java)
                .addTag(Constant.CALL_RECEIVER)
                // .setInputData(data)
                .build()
        WorkManager.getInstance(context.applicationContext)
            .beginUniqueWork(
                Constant.CALL_RECEIVER, ExistingWorkPolicy.KEEP,
                callLogWorkRequest
            ).enqueue()


    }

    protected  fun onIncomingCallAnswered(context: Context,phoneNumber : String?){

        Log.d(Constant.TAG,"onCallAnswered"+ phoneNumber)
       // OverlayService.stopServiceWithoutEndPopuCall()
    }

    protected fun onIncomingCallEnded(context: Context, phoneNumber : String?) {


        Log.d(Constant.TAG,"onIncomingCallEnded"+ phoneNumber)
        // Call End
       // OverlayService.stopService()
        NotifyService.callNotification(mcontext = context!!,callType = Constant.EndCall, phoneNumber = phoneNumber)
    }
    protected fun onOutgoingCallEnded(context: Context,phoneNumber : String?) {

        Log.d(Constant.TAG,"onOutgoingCallEnded"+ phoneNumber)
        // Call End
       // OverlayService.stopService()
        NotifyService.callNotification(mcontext = context!!,callType = Constant.EndCall, phoneNumber = phoneNumber)
    }
    protected fun onMissedCall(context: Context,phoneNumber : String?) {

        NotifyService.callNotification(mcontext = context!!,callType = Constant.MissedCall, phoneNumber = phoneNumber)

        Log.d(Constant.TAG,"onMissedCall"+ phoneNumber)
        // Call End : Missed Call
       // OverlayService.stopService1(context)
       // OverlayService.stopService()
    }


    // region Logic of Call Handling
    fun onCallStateChanged(context: Context?, state: Int , phoneNumber : String?) {


         lastState = SharePrefernce(context!!).readLastState()
        //region comment

        when (state) {
            TelephonyManager.CALL_STATE_RINGING -> {
                //isInComingCall = true
                SharePrefernce(context!!).saveIsCallInComming(true)
                //callStartTime = Date()

                onIncomingCallStarted(context!!, savedNumber)
            }
            TelephonyManager.CALL_STATE_OFFHOOK ->                 //Transition of ringing->offhook are pickups of incoming calls.  Nothing done on them
                if (lastState != TelephonyManager.CALL_STATE_RINGING) {
                   // isInComingCall = false
                    SharePrefernce(context!!).saveIsCallInComming(false)
                    //callStartTime = Date()
                    onOutgoingCallStarted(context!!, savedNumber)
                }
                else
                {

                     //callStartTime =  Date();
                    onIncomingCallAnswered(context, savedNumber);
                    SharePrefernce(context!!).saveIsCallInComming(false)
                }
            TelephonyManager.CALL_STATE_IDLE ->                 //Went to idle-  this is the end of a call.  What type depends on previous state(s)
                if (lastState == TelephonyManager.CALL_STATE_RINGING) {
                    //Ring but no pickup-  a miss
                    onMissedCall(context!!, savedNumber)
                } else if (SharePrefernce(context!!).readIsCallInComming()) {
                    onIncomingCallEnded(context!!, savedNumber)
                } else {
                    onOutgoingCallEnded(context!!, savedNumber)
                }
        }

        //endregion

        SharePrefernce(context!!).saveLastState(state)
       // lastState = state     // save in local db


        //endregion



    }

    fun getPhoneNumber(context: Context?){

        if (ActivityCompat.checkSelfPermission(
                context!!.applicationContext,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission check

            // Create obj of TelephonyManager and ask for current telephone service
            val telephonyManager = context!!.applicationContext.getSystemService(AppCompatActivity.TELEPHONY_SERVICE) as TelephonyManager
            val phoneNumber = telephonyManager.line1Number
            Log.d(Constant.TAG,"" + phoneNumber)
            return
        } else {
            // Ask for permission
            //  requestPermission()
        }

        //  phone_number!!.text = phoneNumber

    }




    //endregion




}