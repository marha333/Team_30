package at.team30.setroute.Helper

import at.team30.setroute.R
import at.team30.setroute.models.Route

class RouteIconHelper {
    companion object {
        fun getRouteTypeIconIdentifier(routeType: Route.RouteType?, light_image: Boolean): Int {
            if (light_image){
                when (routeType){
                    Route.RouteType.ALCOHOLICS->{return R.drawable.ic_baseline_beer_white}
                    Route.RouteType.DOG_WALK->{return R.drawable.ic_baseline_pets_white}
                    Route.RouteType.COFFEE_LOVERS->{return R.drawable.ic_baseline_coffee_white}
                    Route.RouteType.ROMANTIC_WALK->{return R.drawable.ic_baseline_romantic_walk_white}
                    Route.RouteType.SIGHTSEEING_ADDICTED->{return R.drawable.ic_baseline_sights_white}
                    Route.RouteType.SPORT_FREAKS->{return R.drawable.ic_baseline_sport_freaks_white}
                    Route.RouteType.PARK_LOVERS->{return R.drawable.ic_baseline_park_lovers_white}
                    else->{return R.drawable.ic_map_light}
                }
            }
            else {
                when (routeType){
                    Route.RouteType.ALCOHOLICS->{return R.drawable.ic_baseline_beer_black}
                    Route.RouteType.DOG_WALK->{return R.drawable.ic_baseline_pets_black}
                    Route.RouteType.COFFEE_LOVERS->{return R.drawable.ic_baseline_coffee_black}
                    Route.RouteType.ROMANTIC_WALK->{return R.drawable.ic_baseline_romantic_walk_black}
                    Route.RouteType.SIGHTSEEING_ADDICTED->{return R.drawable.ic_baseline_sights_black}
                    Route.RouteType.SPORT_FREAKS->{return R.drawable.ic_baseline_sport_freaks_black}
                    Route.RouteType.PARK_LOVERS->{return R.drawable.ic_baseline_park_lovers_black}
                    else->{return R.drawable.ic_map}
                }
            }
        }
    }
}