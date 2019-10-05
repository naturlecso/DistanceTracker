package hu.naturlecso.distancetracker.data.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationService(
    private val context: Context,
    private val notificationManager: NotificationManager
) {

    var notificationIntent: Intent? = null

    private val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(android.R.drawable.ic_dialog_map)
        .setContentTitle("Distance Tracker")
        .setContentText("Distance tracking is in progress!")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(false)
        .setOnlyAlertOnce(true)

    fun sendNotification() {
        notificationBuilder
            .setContentIntent(notificationIntent?.let {
                PendingIntent.getActivity(context, 0, it, 0)
            })

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "DistanceTracker"
            val mChannel = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(mChannel)
            notificationBuilder.setChannelId(CHANNEL_ID)
        }

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }
    
    fun removeNotification() {
        notificationManager.cancel(NOTIFICATION_ID)
    }

    companion object {
        private const val CHANNEL_ID = "channel_01"
        private const val NOTIFICATION_ID = 1349
    }
}
