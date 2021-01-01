package fr.im.salimi.projectmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

    }

}