package fr.im.salimi.projectmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase

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
}