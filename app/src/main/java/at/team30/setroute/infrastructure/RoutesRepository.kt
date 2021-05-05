package at.team30.setroute.infrastructure

import at.team30.setroute.models.Route

class RoutesRepository : IRoutesRepository {

    val description_coffee : String = "Short relaxing walk through Graz most famous coffee shops."
    val description_coffee_de : String = "Kurzer entspannender Spaziergang durch die bekanntesten Grazer Cafés."
    val description_coffee_ru : String = "Короткая расслабляющая прогулка по самым известным кофейням Граца."

    val description_coffee2 : String = "Long walk through Graz most famous coffee shops."
    val description_coffee2_de : String = "Langer Spaziergang durch die berühmtesten Grazer Cafés."
    val description_coffee2_ru : String = "Долгая прогулка по самым известным кофейням Граца."

    val description_parks : String = "Short walk through Graz most beautiful parks."
    val description_parks_de : String = "Kurzer Spaziergang durch die schönsten Grazer Parks."
    val description_parks_ru : String = "Короткая прогулка по красивейшим паркам Граца."

    val description_sightseeing : String = "Longer walk through Graz most beautiful momuments."
    val description_sightseeing_de : String = "Längerer Spaziergang durch die schönsten Denkmäler von Grazes."
    val description_sightseeing_ru : String = "Более длительная прогулка по красивейшим памятникам Граца."

    private val routes : List<Route> = listOf(
        Route(1,
            "Coffee", "Kaffee", "Кофе",
            Route.RouteType.COFFEE_LOVERS, 10, 0.5,
            description_coffee, description_coffee_de, description_coffee_ru),
        Route(2, "Coffee2", "Kaffee2", "Кофе2",
            Route.RouteType.COFFEE_LOVERS, 15, 0.7,
            description_coffee2, description_coffee2_de, description_coffee2_ru),
        Route(3, "Parks", "Park", "Парки",
            Route.RouteType.PARK_LOVERS, 20, 3.7,
            description_parks, description_parks_de, description_parks_ru),
        Route(4, "Parks2", "Park", "Парки",
            Route.RouteType.PARK_LOVERS, 30, 5.9,
            description_parks, description_parks_de, description_parks_ru),
        Route(5, "Sights", "Sehenswürdigkeiten", "Достопримечательности",
            Route.RouteType.SIGHTSEEING_ADDICTED, 20, 6.9,
            description_sightseeing, description_sightseeing_de, description_sightseeing_ru),
        Route(6, "Sights2", "Sehenswürdigkeiten", "Достопримечательности",
            Route.RouteType.SIGHTSEEING_ADDICTED, 30, 10.6,
            description_sightseeing, description_sightseeing_de, description_sightseeing_ru),
        Route(7, "Sights3", "Sehenswürdigkeiten", "Достопримечательности",
            Route.RouteType.SIGHTSEEING_ADDICTED, 40, 15.2,
            description_sightseeing, description_sightseeing_de, description_sightseeing_ru))

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