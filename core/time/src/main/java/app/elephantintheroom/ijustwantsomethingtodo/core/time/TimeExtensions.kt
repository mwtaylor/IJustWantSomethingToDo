package app.elephantintheroom.ijustwantsomethingtodo.core.time

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

private fun Instant.atDefaultZone(): ZonedDateTime {
    return atZone(ZoneId.systemDefault())
}

private fun LocalDate.atStartOfDayInDefaultZone(): ZonedDateTime =
    atStartOfDay(ZoneId.systemDefault())

fun Instant.atStartOfDay(): Instant =
    LocalDate.from(atDefaultZone())
        .atStartOfDayInDefaultZone()
        .toInstant()
