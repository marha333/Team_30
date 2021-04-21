package at.team30.setroute.ui

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import at.team30.setroute.R
import dagger.hilt.android.AndroidEntryPoint
import at.team30.setroute.application.AppPermissions
import com.google.android.material.bottomnavigation.BottomNavigationView

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()

        AppPermissions.checkAndSetupPermission(this, Manifest.permission.ACCESS_FINE_LOCATION,
            AppPermissions.RequestCode.ACCESS_LOCATION_CODE)
    }

    private fun setupViews()
    {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(findViewById<BottomNavigationView>(R.id.bottom_nav), navHostFragment.navController)
    }
}