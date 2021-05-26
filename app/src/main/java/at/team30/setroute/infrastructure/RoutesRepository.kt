package at.team30.setroute.infrastructure

import android.widget.Switch
import at.team30.setroute.R
import at.team30.setroute.models.Route
import com.google.android.gms.maps.model.LatLng


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

    val description_dog_walk : String = "A dog walk through Graz."
    val description_dog_walk_de : String = "Ein Spaziergang mit dem Hund durch Graz."
    val description_dog_walk_ru : String = "Прогулка по Грацу с собакой."

    val description_alcoholics : String = "An alcohol tour through best pubs i Graz."
    val description_alcoholics_de : String = "Ein Spaziergang für Alkohol-Enthusiasten."
    val description_alcoholics_ru : String = "Избранные пивнушки Граца."

    val description_romantic_walk : String = "Romantic walk through Graz."
    val description_romantic_walk_de : String = "Ein romantischer Spaziergang."
    val description_romantic_walk_ru : String = "Романтическая прогулка по городу."

    val description_sport_freaks : String = "Intensive training."
    val description_sport_freaks_de : String = "Ein intensives Training."
    val description_sport_freaks_ru : String = "Интенсивный тренинг."

    private val routes : List<Route> = listOf(
        Route(1,
            "Coffee", "Kaffee", "Кофе",
            Route.RouteType.COFFEE_LOVERS, 10, 0.5,
            description_coffee, description_coffee_de, description_coffee_ru,
            listOf(
                LatLng(47.068291509777374,15.4505505), //47.068291509777374, 15.4505505 //TRibeka technikerstraße
                LatLng(47.06846198659862, 15.434199801525855))),//47.06846198659862, 15.434199801525855 omas teekanne),
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
            description_sightseeing, description_sightseeing_de, description_sightseeing_ru),
        Route(8, "Dog walk", "Hunde-Runde", "Прогулка с собакой",
            Route.RouteType.DOG_WALK, 30, 2.0,
            description_dog_walk, description_dog_walk_de, description_dog_walk_ru),
        Route(9, "Alcoholics", "Beisl-Tour", "Алкотур",
            Route.RouteType.ALCOHOLICS, 60, 3.0,
            description_alcoholics, description_alcoholics_de, description_alcoholics_ru),
        Route(10, "Romantic walk", "Romantischer Spaziergang", "Романтическая прогулка",
            Route.RouteType.ROMANTIC_WALK, 35, 1.5,
            description_romantic_walk, description_romantic_walk_de, description_romantic_walk_ru),
        Route(11, "Sport freaks", "Sport Freak", "Спортсмен",
            Route.RouteType.SPORT_FREAKS, 42, 1.5,
            description_sport_freaks, description_sport_freaks_de, description_sport_freaks_ru)
    )



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