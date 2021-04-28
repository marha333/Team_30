package at.team30.setroute.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import at.team30.setroute.R
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng


/// Source: https://medium.com/@paultr/google-maps-for-android-pt-2-user-location-f7416966aa67
class MapsFragment : Fragment(), GoogleMap.OnMyLocationButtonClickListener {

    private lateinit var mMap: GoogleMap;
    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val locationPermission = 101

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapsFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        mapsFragment?.getMapAsync { googleMap ->
            mMap = googleMap
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                initMap()
            } else {
                this.requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermission)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun initMap() {
        mMap.isMyLocationEnabled = true
        initLocationTracking()
    }

    @SuppressLint("MissingPermission")
    private fun initLocationTracking() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations){
                    updateMapLocation(location)
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(
                LocationRequest(),
                locationCallback,
                Looper.getMainLooper())
    }

    @SuppressLint("MissingPermission")
    override fun onMyLocationButtonClick(): Boolean {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            updateMapLocation(location)
        }
        return true
    }

    private fun updateMapLocation(location: Location?) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(
                location?.latitude ?: 0.0,
                location?.longitude ?: 0.0)))
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15.0f))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) { locationPermission ->
            if (grantResults.isNotEmpty() && permissions.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMap()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if( ::mMap.isInitialized ) {
            initLocationTracking()
        }
    }

    override fun onPause() {
        super.onPause()
        if(::locationCallback.isInitialized) {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
}