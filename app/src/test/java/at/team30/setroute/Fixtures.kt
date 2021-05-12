package at.team30.setroute

import at.team30.setroute.models.Route
import com.google.android.gms.maps.model.LatLng

class Fixtures {
    companion object {
        fun routes_one() : List<Route> = listOf(
            Route(1, "Test1", "Test_DE_1", "Тест_1", Route.RouteType.COFFEE_LOVERS, 1, 1.1, "Description", "Beschreibung", "Описание")
        )

        fun routes_many() : List<Route> = listOf(
            Route(1, "Test1", "Test_DE_1", "Тест_1", Route.RouteType.COFFEE_LOVERS, 1, 1.1, "Description", "Beschreibung", "Описание"),
            Route(2, "Test2", "Test_DE_2", "Тест_2", Route.RouteType.COFFEE_LOVERS, 1, 1.2, "Description", "Beschreibung", "Описание"),
            Route(3, "Test3", "Test_DE_3", "Тест_3", Route.RouteType.COFFEE_LOVERS, 1, 1.3, "Description", "Beschreibung", "Описание"),
        )

        fun routes_navigation_test() : List<Route> = listOf(
           Route(1, "0wp", "0wp", "0wp", Route.RouteType.COFFEE_LOVERS, 1, 1.1, "Description", "Beschreibung", "Описание",
                 listOf(
                   LatLng(47.068291509777374,15.4505505),
                   LatLng(47.06846198659862, 15.434199801525855))),
           Route(2, "1wp", "1wp", "1wp", Route.RouteType.COFFEE_LOVERS, 1, 1.2, "Description", "Beschreibung", "Описание",
                   listOf(
                   LatLng(47.068291509777374,15.4505505),
                   LatLng(47.066792160267376, 15.441534570967487),
                   LatLng(47.06846198659862, 15.434199801525855))),
           Route(3, "many_wp", "many_wp", "many_wp", Route.RouteType.COFFEE_LOVERS, 1, 1.3, "Description", "Beschreibung", "Описание",
                   listOf(
                   LatLng(47.068291509777374,15.4505505),
                   LatLng(47.066792160267376, 15.441534570967487),
                   LatLng(47.06322620536392, 15.436719303092854),
                   LatLng(47.06846198659862, 15.434199801525855)))
        )
    }
}