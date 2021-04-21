package at.team30.setroute.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import at.team30.setroute.R
import at.team30.setroute.application.AppPermissions

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private lateinit var mMap: GoogleMap;
    private var locationAccessGranted: Int = -1
    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */


        Log.d("MAP", "Inside Map Ready Callback")
        mMap = googleMap

        locationAccessGranted = AppPermissions.checkAndSetupPermission(context as AppCompatActivity, Manifest.permission.ACCESS_FINE_LOCATION,
                AppPermissions.RequestCode.ACCESS_LOCATION_CODE)
        var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION);
        requestPermissions(permissions, AppPermissions.RequestCode.ACCESS_LOCATION_CODE.value);

        if(locationAccessGranted == PackageManager.PERMISSION_GRANTED){
            mMap!!.isMyLocationEnabled=true
            Log.d("MAP", "PERMISSION GRANTED")
        }else {
            mMap!!.isMyLocationEnabled=false
            Log.d("MAP", "NO PERMISSION")
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.d("MAP", "CALLBACK")
        locationAccessGranted = grantResults[0]

        if(this::mMap.isInitialized){
            if(locationAccessGranted == PackageManager.PERMISSION_GRANTED){
                mMap!!.isMyLocationEnabled=true
                Log.d("MAP", "PERMISSION GRANTED")
            }else {
                mMap!!.isMyLocationEnabled = false
                Log.d("MAP", "NO PERMISSION")
            }
        }else{
            Log.d("MAP", "null")

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}