package at.team30.setroute.models

import at.team30.setroute.R

data class SortingOptions(
    val order: Order = Order.DESCENDING,
    val field: Field = Field.TITLE
) {
    companion object {
        fun fromValues(order: Int, field: String): SortingOptions {
            return SortingOptions(Order.fromValue(order), Field.fromString(field))
        }
    }
}

enum class Order {
    ASCENDING,
    DESCENDING;

    companion object {
        fun fromValue(order: Int): Order {
            return when (order) {
                R.id.sort_ascending -> ASCENDING
                R.id.sort_descending -> DESCENDING
                else -> ASCENDING
            }
        }
    }
}

enum class Field {
    TITLE,
    DISTANCE,
    DURATION;

    companion object {
        fun fromString(field: String): Field {
            return when (field) {
                "title" -> TITLE
                "duration" -> DURATION
                "length" -> DISTANCE
                else -> TITLE
            }
        }
    }
}