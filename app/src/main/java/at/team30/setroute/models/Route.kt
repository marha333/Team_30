package at.team30.setroute.models

data class Route(val id:Int, val name:String, val type:RouteType, val duration: Int, val length : Double, val description : String) {


    enum class RouteType (val value : Int) {
        PARK_LOVERS(1),
        SIGHTSEEING_ADDICTED(2),
        COFFEE_LOVERS(3),
    }
}
