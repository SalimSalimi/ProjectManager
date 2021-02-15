package fr.im.salimi.projectmanager.receivers

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import fr.im.salimi.projectmanager.utils.sendNotification

class NotificationAlarmReceiver: BroadcastReceiver(){

    override fun onReceive(context: Context, intent: Intent?) {
        val notificationManager = ContextCompat.getSystemService(context, NotificationManager::class.java) as NotificationManager
        notificationManager.sendNotification(
                context,
                "Hello World!"
        )
    }
}