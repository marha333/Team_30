package at.team30.setroute.models

class FilteringOptions (
    var interests: List<Int> = listOf(0, 1, 2, 3, 4, 5, 6),
    var minDistance: Int = 0,
    var maxDistance: Int = 20,
    var minDuration: Int = 0,
    var maxDuration: Int = 120) {
    companion object {
        fun fromValues(interests: List<Int>, minDistance: Int, maxDistance: Int, minDuration: Int, maxDuration: Int): FilteringOptions {
            return FilteringOptions(interests, minDistance, maxDistance, minDuration, maxDuration)
        }
    }
}