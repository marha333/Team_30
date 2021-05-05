package at.team30.setroute

import at.team30.setroute.models.Route

class FixturesAndroid {
    companion object {
        fun routes_one() : List<Route> = listOf(
            Route(1, "Test1", "Test_DE_1", "Тест_1", Route.RouteType.COFFEE_LOVERS, 1, 1.1, "Description", "Beschreibung", "Описание")
        )

        fun routes_many() : List<Route> = listOf(
            Route(1, "Test1", "Test_DE_1", "Тест_1", Route.RouteType.COFFEE_LOVERS, 1, 1.1, "Description", "Beschreibung", "Описание"),
            Route(2, "Test2", "Test_DE_2", "Тест_2", Route.RouteType.COFFEE_LOVERS, 1, 1.2, "Description", "Beschreibung", "Описание"),
            Route(3, "Test3", "Test_DE_3", "Тест_3", Route.RouteType.COFFEE_LOVERS, 1, 1.3, "Description", "Beschreibung", "Описание"),
        )
    }
}