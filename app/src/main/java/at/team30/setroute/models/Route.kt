package at.team30.setroute.models

import com.google.android.gms.maps.model.LatLng

data class Route(
    val id:Int,
    val name:String,
    val name_de:String,
    val name_ru:String,
    val type:RouteType,
    val duration: Int,
    val length : Double,
    val description : String,
    val description_de : String,
    val description_ru : String,
    val positions: List<LatLng>? = null
) {

    enum class RouteType (val value : Int) {
        PARK_LOVERS(1),
        SIGHTSEEING_ADDICTED(2),
        COFFEE_LOVERS(3),
    }

    fun getLocalizedName(code: String): String {
        return when(Language.forCode(code)) {
            Language.ENGLISH -> this.name
            Language.GERMAN -> this.name_de
            Language.RUSSIAN -> this.name_ru
        }
    }

    fun getLocalizedDescription(code: String): String {
        return when(Language.forCode(code)) {
            Language.ENGLISH -> this.description
            Language.GERMAN -> this.description_de
            Language.RUSSIAN -> this.description_ru
        }
    }
}
