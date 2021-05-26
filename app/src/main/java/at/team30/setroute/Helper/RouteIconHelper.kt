package at.team30.setroute.Helper

import at.team30.setroute.R
import at.team30.setroute.models.Route

class RouteIconHelper {
    companion object {
        fun getRouteTypeIconIdentifier(routeType: Route.RouteType?): Int {
            return when (routeType){
                Route.RouteType.ALCOHOLICS -> R.drawable.ic_baseline_beer_white
                Route.RouteType.DOG_WALK -> R.drawable.ic_baseline_pets_white
                Route.RouteType.COFFEE_LOVERS -> R.drawable.ic_baseline_coffee_white
                Route.RouteType.ROMANTIC_WALK -> R.drawable.ic_baseline_romantic_walk_white
                Route.RouteType.SIGHTSEEING_ADDICTED -> R.drawable.ic_baseline_sights_white
                Route.RouteType.SPORT_FREAKS -> R.drawable.ic_baseline_sport_freaks_white
                Route.RouteType.PARK_LOVERS -> R.drawable.ic_baseline_park_lovers_white
                else -> R.drawable.ic_map_light
            }
        }
    }
}