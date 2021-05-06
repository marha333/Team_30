package at.team30.setroute.application

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import at.team30.setroute.application.AppPermissions.Companion.requestPermission

class AppPermissions {
    companion object {
        fun checkAndSetupPermission(activity: AppCompatActivity, permission: String, requestCode: RequestCode): Int {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermission(activity, permission, requestCode)
            }
            return ContextCompat.checkSelfPermission(activity, permission);
        }


        private fun requestPermission(activity : AppCompatActivity, permission: String, requestCode: RequestCode) {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode.value)
        }
    }

    enum class RequestCode (val value : Int) {
        ACCESS_LOCATION_CODE(101) // add more if you need
    }
}