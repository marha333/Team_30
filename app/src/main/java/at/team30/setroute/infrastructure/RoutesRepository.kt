package at.team30.setroute.infrastructure

import at.team30.setroute.models.Route

class RoutesRepository : IRoutesRepository {

    private val routes : List<Route> = listOf(
        Route("route1"),
        Route("route2"),
        Route("route3"))

    override fun getRoutes(): List<Route> {
        return routes
    }
}