package at.team30.setroute.Helper

import at.team30.setroute.models.Route
import com.google.android.gms.maps.model.LatLng

class NavigationHelper {
    companion object {
        val linkBase : String = "https://www.google.com/maps/dir/?api=1&"

        fun getNavLink(route : Route): String {
            var link : String = linkBase
            var originString = ""
            var destinationString = ""
            var waypointString = ""

            val travelMode = "travelmode=walking"


            if (route != null) {
                if (route.positions != null && route.positions.isNotEmpty()) {
                    var originCoordinates: LatLng = route.positions[0]
                    originString = "origin=" + originCoordinates.latitude + "," + originCoordinates.longitude

                    var destinationCoordinates: LatLng = route.positions.last()
                    destinationString = "destination=" + destinationCoordinates.latitude + "," + destinationCoordinates.longitude

                    waypointString = "waypoints="

                    route.positions.forEach {
                        if (it == originCoordinates || it == destinationCoordinates) {}
                        else {
                            waypointString = waypointString + it.latitude + "," + it.longitude + "%7C"
                        }
                    }
                }



                link = "$link$originString&$destinationString&$travelMode&$waypointString"
            }

            return link
        }
    }
}