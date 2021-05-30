package at.team30.setroute.models

class FilteringOptions (
    var interests: List<Int> = listOf(0, 1, 2, 3, 4, 5, 6),
    var minDistance: Float = 0f,
    var maxDistance: Float = 20f,
    var minDuration: Float = 0f,
    var maxDuration: Float = 120f) {
    companion object {
        fun fromValues(interests: List<Int>, minDistance: Float, maxDistance: Float, minDuration: Float, maxDuration: Float): FilteringOptions {
            return FilteringOptions(interests, minDistance, maxDistance, minDuration, maxDuration)
        }
    }
}