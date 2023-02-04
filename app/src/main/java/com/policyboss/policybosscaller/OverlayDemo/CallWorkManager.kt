package com.policyboss.policybosscaller.OverlayDemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.example.policybosscaller.Utility.Constant
import com.policyboss.policybosscaller.Home.HomeActivity
import com.policyboss.policybosscaller.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CallWorkManager (var context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    override suspend fun doWork(): Result {

        return try {
            Log.d("CallLogWorker", "Run work manager")
            //Do Your task here

            callLogTask()

            // Log.d("CallLogWorker", callLogList.toString())
            val outPutData: Data = Data.Builder()
                .putString(Constant.TAG, "Data Uploaded Successfully..")

                .build()
            Result.success(outPutData)
        }
        catch (e: Exception) {
            Log.d(Constant.TAG, "exception in doWork1 ${e.message}")
            val errorData: Data = Data.Builder()
                .putString(Constant.TAG, "Data Not Uploaded.Please Try Again.")
                .build()
            Result.failure(errorData)

        }
    }

    private suspend fun callLogTask() {

        var strbody = "Contact is Uploading Please wait.."
        var strResultbody = "Successfully Uploaded.."
        setForeground(createForegroundInfo( strbody))



        //delay(2000)

        withContext(Dispatchers.IO) {

            // Log.d(TAG, "maxProgress ${maxProgress}")
            setForeground(createForegroundInfo(strbody))

            withContext(Dispatchers.Main) {

                CustomWIndow.openWindowPopUp(
                    applicationContext,
                    callType = Constant.StartCall,
                    PhoneNumber = "****"
                )
            }


        }



    }


    //region Creates notifications for service
    private fun createForegroundInfo(

        strbody: String
    ): ForegroundInfo {



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(   Constant.CHANNEL_ID, Constant.CHANNEL_NAME,)
        }


        val notificationIntent = Intent(applicationContext, HomeActivity::class.java)

        val flag = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            PendingIntent.FLAG_IMMUTABLE
        }else {0 }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0, notificationIntent, flag
        )


     val notification = NotificationCompat.Builder(applicationContext, Constant.CHANNEL_ID)
            .setContentTitle("PolicyBoss Pro Caller is Enable")
            .setSmallIcon(R.drawable.ic_call_24)
            .setContentIntent(pendingIntent)
            // .setFullScreenIntent(pendingIntent,true)
            .setColor(Color.GREEN)
            .setAutoCancel(true)
            .build()


        return ForegroundInfo(12, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel1(id: String, channelName: String) {
        notificationManager.createNotificationChannel(
            NotificationChannel(id, channelName, NotificationManager.IMPORTANCE_DEFAULT)

        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createChannel(id: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                id,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.enableLights(true)
            channel.enableVibration(true)
            channel.lightColor = Color.BLUE
            channel.description = "PoliyBoss Pro"
            // Sets whether notifications posted to this channel appear on the lockscreen or not
            channel.lockscreenVisibility =
                Notification.VISIBILITY_PUBLIC // Notification.VISIBILITY_PRIVATE
            notificationManager.createNotificationChannel(channel)
        }
    }

    //endregion

}