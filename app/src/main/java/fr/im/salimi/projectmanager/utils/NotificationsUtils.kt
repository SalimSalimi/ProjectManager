package fr.im.salimi.projectmanager.utils

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import fr.im.salimi.projectmanager.R

const val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(applicationContext: Context, messageBody: String) {
    val builder = NotificationCompat.Builder(
            applicationContext,
            applicationContext.getString(R.string.notification_tasks_channel_id)
    )
            .setContentTitle(applicationContext.getString(R.string.app_name))
            .setContentText(messageBody)
            .setSmallIcon(R.drawable.ic_baseline_tasks_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

    notify(NOTIFICATION_ID, builder.build())

}