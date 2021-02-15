package fr.im.salimi.projectmanager

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.AlarmManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.receivers.NotificationAlarmReceiver

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fabBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(findViewById(R.id.toolbar))
        bottomNavigation = findViewById(R.id.bottom_navigation_main)
        fabBtn = findViewById(R.id.fab_main)

        bottomNavigation.background = null
        val navController = findNavController(R.id.nav_host_fragment)

        bottomNavigation.setupWithNavController(navController)

        ProjectRoomDatabase.createInstance(applicationContext)
        createNotificationChannel(applicationContext.getString(R.string.notification_tasks_channel_id),
                applicationContext.getString(R.string.notification_tasks_name))

        createNotificationAlarm()
    }

    private fun createNotificationChannel(channelID: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                    channelID,
                    channelDescription,
                    NotificationManager.IMPORTANCE_HIGH
            )
            with(notificationChannel) {
                enableLights(true)
                enableVibration(true)
                lightColor = Color.RED
                description = applicationContext.getString(R.string.notification_tasks_description)
            }
            val notificationManager = applicationContext.getSystemService(NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createNotificationAlarm() {
        val alarmManger = applicationContext.getSystemService(ALARM_SERVICE) as AlarmManager
        val notificationBroadcast = Intent(this, NotificationAlarmReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(applicationContext,
                0,
                notificationBroadcast,
                PendingIntent.FLAG_UPDATE_CURRENT)

        val triggerTime = SystemClock.elapsedRealtime() + 10_000L

        AlarmManagerCompat.setExactAndAllowWhileIdle(
                alarmManger,
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerTime,
                pendingIntent
        )
    }
}