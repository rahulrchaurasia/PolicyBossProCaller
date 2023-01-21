package com.example.policybosscaller.Utility

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.policyboss.policybosscaller.Home.HomeActivity
import com.policyboss.policybosscaller.R


/**
 * Created by Rahul on 10/05/2022.
 */
object NotifyService {

    private val CHANNEL_ID = Constant.CHANNEL_ID
    private val CHANNEL_NAME =  Constant.CHANNEL_NAME
    var NOTIFICATION_ID = 5



    fun createNotificationChannels(mcontext : Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                enableLights(true)
                enableVibration(true)
                lightColor = Color.GREEN
                description = "policybosscaller"

                lockscreenVisibility =
                    Notification.VISIBILITY_PUBLIC // Notification.VISIBILITY_PRIVATE
            }


            val notificationManager = mcontext.getSystemService( Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }



    }

    fun Flag() : Int{

        // Todo : Check flag
        val flag = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            PendingIntent.FLAG_IMMUTABLE

        }else {
            0
        }
        return flag
    }

     fun callNotification(  mcontext : Context,  callType : String,  phoneNumber : String? = "" ) {

            createNotificationChannels(mcontext)


            val intentHome = Intent(mcontext, HomeActivity::class.java)

         val flag = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
             PendingIntent.FLAG_IMMUTABLE
         }else {0 }
         val pendingIntent = PendingIntent.getActivity(
             mcontext,
             5, intentHome, flag
         )



// .setContentText("${callType}: ${phoneNumber!!.takeLast(10)}")
            val notification = NotificationCompat.Builder(mcontext,CHANNEL_ID)
                .setContentTitle("${callType}: ${phoneNumber!!.takeLast(10)}")
                .setSmallIcon(R.drawable.ic_call_24)
                .setColor(Color.GREEN)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build()

            val notificationManager = NotificationManagerCompat.from(mcontext)
            notificationManager.notify(NOTIFICATION_ID,notification)



        }




}