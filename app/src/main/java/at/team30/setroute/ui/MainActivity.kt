package at.team30.setroute.ui

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import at.team30.setroute.R
import at.team30.setroute.application.AppPermissions
import at.team30.setroute.ui.settings.SettingsFragment
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
        val sharedPreference = getSharedPreferences(SettingsFragment.SHARED_PREF_KEY, Context.MODE_PRIVATE)
        val nightModeEnabled = sharedPreference.getBoolean(SettingsFragment.DM_PREF_KEY, false)
        AppCompatDelegate.setDefaultNightMode(if (nightModeEnabled) MODE_NIGHT_YES else MODE_NIGHT_NO)

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