package at.team30.setroute.models

import java.util.*

enum class Language(val code: String) {
    ENGLISH("EN"),
    GERMAN("DE"),
    RUSSIAN("RU");

    companion object  {
        fun forCode(code: String): Language {
            return when (code.toUpperCase(Locale.ROOT)) {
                "EN" -> ENGLISH
                "DE" -> GERMAN
                "RU" -> RUSSIAN
                else -> ENGLISH
            }
        }
    }
}
