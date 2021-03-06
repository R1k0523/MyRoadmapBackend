package ru.boringowl.myroadmap.domain

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
class Hackathon {
    var hackId: UUID? = null
    var hackTitle: String = ""
    var hackDescription: String = ""
    var publishDate: LocalDateTime = LocalDateTime.now()
    var source: String = ""
    var date: String? = ""
    var registration: String? = ""
    var focus: String? = ""
    var prize: String? = ""
    var routes: String? = ""
    var terms: String? = ""
    var organization: String? = ""
    var imageUrl: String? = ""

    fun fullText(): String =
        listOf(hackTitle,
            hackDescription,
            date,
            registration,
            focus,
            prize,
            routes,
            terms,
            organization,
            imageUrl,
        ).joinToString(" ").lowercase()

    fun containsText(query: String) = fullText().contains(query.lowercase())
}
