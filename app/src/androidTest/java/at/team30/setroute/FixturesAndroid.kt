package at.team30.setroute

import at.team30.setroute.models.Route

class FixturesAndroid {
    companion object {
        fun routes_one() : List<Route> = listOf(
            Route(1, "Test1", Route.RouteType.COFFEE_LOVERS, 1, 1.1, "Description")
        )

        fun routes_many() : List<Route> = listOf(
            Route(1, "Test1", Route.RouteType.COFFEE_LOVERS, 1, 1.1, "Description"),
            Route(2, "Test2", Route.RouteType.COFFEE_LOVERS, 1, 1.2, "Description"),
            Route(3, "Test3", Route.RouteType.COFFEE_LOVERS, 1, 1.3, "Description"),
        )
    }
}