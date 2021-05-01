package at.team30.setroute.models

import android.graphics.Bitmap
import android.media.Image
import com.google.android.gms.maps.model.LatLng


data class Route(val id:Int, val name:String, val type:RouteType, val duration: Int, val length: Double, val description: String, val positions: List<LatLng>? = null, var image: Bitmap? = null) {


    enum class RouteType (val value : Int) {
        PARK_LOVERS(1),
        SIGHTSEEING_ADDICTED(2),
        COFFEE_LOVERS(3),
    }
}
