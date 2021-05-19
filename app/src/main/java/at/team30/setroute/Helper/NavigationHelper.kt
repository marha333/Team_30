package at.team30.setroute.Helper

import at.team30.setroute.models.Route
import com.google.android.gms.maps.model.LatLng

class NavigationHelper {
    companion object {
        const val linkBase = "https://www.google.com/maps/dir/?api=1&"
        const val travelMode = "travelmode=walking"
        const val dirAction = "dir_action=navigate"

        fun getNavLink(route : Route): String {
            var originString = ""
            var destinationString = ""
            var waypointString = ""

            if (route != null) {
                if (route.positions != null && route.positions.isNotEmpty()) {
                    val originCoordinates: LatLng = route.positions.first()
                    originString = "origin=${originCoordinates.latitude},${originCoordinates.longitude}"

                    val destinationCoordinates: LatLng = route.positions.last()
                    destinationString = "destination=${destinationCoordinates.latitude},${destinationCoordinates.longitude}"

                    waypointString = "waypoints="

                    route.positions.forEach {
                        if (it == originCoordinates || it == destinationCoordinates) {}
                        else {
                            waypointString = waypointString + it.latitude + "," + it.longitude + "%7C"
                        }
                    }
                }
            }
            return "$linkBase$originString&$destinationString&$travelMode&$dirAction&$waypointString"
        }
    }
}