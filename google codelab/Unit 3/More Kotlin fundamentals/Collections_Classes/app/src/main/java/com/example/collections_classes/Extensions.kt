package com.example.collections_classes

fun List<Event>.summary(): String {
    val shortEvents = count { it.durationInMinutes < 60 }
    val eventCountByDaypart = groupBy { it.daypart }
        .map { "${it.key}: ${it.value.size} events" }
        .joinToString("\n")
    val lastEventTitle = last().title

    return """
        |You have $shortEvents short events.
        |
        |$eventCountByDaypart
        |
        |Last event of the day: $lastEventTitle
    """.trimMargin()
}
