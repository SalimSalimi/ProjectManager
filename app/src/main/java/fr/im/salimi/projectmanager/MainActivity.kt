package fr.im.salimi.projectmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(findViewById(R.id.toolbar))
        bottomNavigation = findViewById(R.id.bottom_navigation_main)
        bottomNavigation.menu.getItem(2).isEnabled = false
        bottomNavigation.background = null
        val navController = findNavController(R.id.nav_host_fragment)

        bottomNavigation.setupWithNavController(navController)

    }

}