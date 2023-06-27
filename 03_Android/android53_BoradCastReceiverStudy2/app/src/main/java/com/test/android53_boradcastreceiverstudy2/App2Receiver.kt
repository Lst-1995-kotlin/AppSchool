package com.test.android53_boradcastreceiverstudy2


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class App2Receiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {

        val data1 = intent.getIntExtra("data1", 0)
        val data2 = intent.getStringExtra("data2")

        val newIntent = Intent(context, MainActivity::class.java)
        newIntent.putExtra("data1", data1)
        newIntent.putExtra("data2", data2)

        val pendingIntent = PendingIntent.getActivity(context, 10,
            newIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        // Create a notification
        val notification = NotificationCompat.Builder(context!!, "Style")
            .setContentTitle("앱1에서 앱2 호출")
            .setContentText("앱1에서 앱2를 호출하였습니다. 전달값1 : ${data1}, 전달값2: ${data2}")
            .setSmallIcon(R.drawable.appschool)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        // Show the notification
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification)
    }

}