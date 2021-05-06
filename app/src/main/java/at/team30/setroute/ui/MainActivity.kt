package at.team30.setroute.ui

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import at.team30.setroute.R
import at.team30.setroute.application.AppPermissions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegate
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegateImpl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : LocaleAwareCompatActivity() {
    private lateinit var navController: NavController
    private val localeDelegate: LocaleHelperActivityDelegate = LocaleHelperActivityDelegateImpl()

    override fun getDelegate(): AppCompatDelegate = localeDelegate.getAppCompatDelegate(super.getDelegate())

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(localeDelegate.attachBaseContext(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreference = getSharedPreferences("test_preferences", Context.MODE_PRIVATE)
        if (sharedPreference.getString("CurrentTheme", "Light") == "Dark")
            this.setTheme(R.style.Theme_SetRoute_Dark)
        else
            this.setTheme(R.style.Theme_SetRoute)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()

        AppPermissions.checkAndSetupPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION,
            AppPermissions.RequestCode.ACCESS_LOCATION_CODE
        )
    }

    private fun setupViews()
    {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(
            findViewById<BottomNavigationView>(R.id.bottom_nav),
            navHostFragment.navController
        )
    }
}