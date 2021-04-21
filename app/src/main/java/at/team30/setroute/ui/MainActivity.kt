package at.team30.setroute.ui

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import at.team30.setroute.R
import at.team30.setroute.application.AppPermissions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*AppPermissions.checkAndSetupPermission(this, Manifest.permission.ACCESS_FINE_LOCATION,
            AppPermissions.RequestCode.ACCESS_LOCATION_CODE)
    */
    }
}