package com.w3engineers.testkt.notify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.w3engineers.testkt.MainActivity
import com.w3engineers.testkt.R

object NotificationUtil {
    private const val PRIMARY_CHANNEL_ID = "gaad-channel"
    private const val ACTION_UPDATE_NOTIFICATION = "architecturecomponent.notify.ACTIO"
    private const val NOTIFICATION_ID = 101

    fun showNotification(context: Context) {
        createNotificationChannel(context);
    }

    private fun createNotificationChannel(context: Context) {
        // Create a notification manager object.
        val mNotifyManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID, context.getString(R.string.app_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Channel description"
            mNotifyManager.createNotificationChannel(notificationChannel)
        }
        showNotification(mNotifyManager, context)
    }

    private fun showNotification(notificationManager: NotificationManager, context: Context) {
        val updateIntent = Intent(ACTION_UPDATE_NOTIFICATION)
        val updatePendingIntent = PendingIntent.getBroadcast(
            context, NOTIFICATION_ID,
            updateIntent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val notifyBuilder: NotificationCompat.Builder? = getNotificationBuilder(context)
        notifyBuilder?.addAction(R.drawable.ic_menu_camera, "click ", updatePendingIntent)

        // Deliver the notification.
        // Deliver the notification.
        notificationManager.notify(NOTIFICATION_ID, notifyBuilder?.build())
    }

    private fun getNotificationBuilder(context: Context): NotificationCompat.Builder? {
        val notificationIntent = Intent(context, MainActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(
            context, NOTIFICATION_ID,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        // Build the notification with all of the parameters.
        return NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
            .setContentTitle("Gaad ")
            .setContentText("Gaad notification dexcription")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true).setContentIntent(notificationPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
    }
}