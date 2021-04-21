package at.team30.setroute.infrastructure

import at.team30.setroute.models.Route

class RoutesRepository : IRoutesRepository {

    val description_coffee : String = "Short relaxing walk through Graz most famous coffee shops."
    val description_coffee2 : String = "Long walk through Graz most famous coffee shops."
    val description_parks : String = "Short walk through Graz most beautiful parks."
    val description_sightseeing : String = "Longer walk through Graz most beautiful momuments."

    private val routes : List<Route> = listOf(
        Route(1, "Coffee", Route.RouteType.COFFEE_LOVERS, 10, 0.5, description_coffee),
        Route(2, "Coffee2", Route.RouteType.COFFEE_LOVERS, 15, 0.7, description_coffee2),
        Route(3, "Parks", Route.RouteType.PARK_LOVERS, 20, 3.7, description_parks),
        Route(4, "Parks2", Route.RouteType.PARK_LOVERS, 30, 5.9, description_parks),
        Route(5, "Sights", Route.RouteType.SIGHTSEEING_ADDICTED, 20, 6.9, description_sightseeing),
        Route(6, "Sights2", Route.RouteType.SIGHTSEEING_ADDICTED, 30, 10.6, description_sightseeing),
        Route(7, "Sights3", Route.RouteType.SIGHTSEEING_ADDICTED, 40, 15.2, description_sightseeing))

    override fun getRoutes(): List<Route> {
        return routes
    }

    override fun getRoutesById(id: Int): Route? {
        return try {
            routes.first { it.id == id }
        } catch (ex: NoSuchElementException) {
            null
        }
    }
}