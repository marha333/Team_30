package at.team30.setroute.infrastructure

import at.team30.setroute.models.Route

class RoutesRepository : IRoutesRepository {

    val description_coffee : String = "Short relaxing walk through Graz most famous coffee shops."
    val description_coffee2 : String = "Long walk through Graz most famous coffee shops."
    val description_parks : String = "Short walk through Graz most beautiful parks."
    val description_sightseeing : String = "Longer walk through Graz most beautiful momuments."


    private val routes : List<Route> = listOf(
        Route("Coffee", Route.RouteType.COFFEE_LOVERS, 10, 0.5, description_coffee),
        Route("Coffee2", Route.RouteType.COFFEE_LOVERS, 15, 0.7, description_coffee2),
        Route("Parks", Route.RouteType.PARK_LOVERS, 20, 3.7, description_parks),
        Route("Parks2", Route.RouteType.PARK_LOVERS, 30, 5.9, description_parks),
        Route("Sights", Route.RouteType.SIGHTSEEING_ADDICTED, 20, 6.9, description_sightseeing),
        Route("Sights2", Route.RouteType.SIGHTSEEING_ADDICTED, 30, 10.6, description_sightseeing),
        Route("Sights3", Route.RouteType.SIGHTSEEING_ADDICTED, 40, 15.2, description_sightseeing))



    override fun getRoutes(): List<Route> {
        return routes
    }
}