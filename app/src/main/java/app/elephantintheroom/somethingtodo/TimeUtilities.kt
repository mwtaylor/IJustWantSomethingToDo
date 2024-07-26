package app.elephantintheroom.somethingtodo

import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields

object TimeUtilities {
    private fun Instant.atDefaultZone(): ZonedDateTime {
        return atZone(ZoneId.systemDefault())
    }

    private fun LocalDate.atStartOfDayInDefaultZone(): ZonedDateTime {
        return atStartOfDay(ZoneId.systemDefault())
    }

    fun Instant.startOfDay(): Instant {
        return LocalDate.from(atDefaultZone())
            .atStartOfDayInDefaultZone()
            .toInstant()
    }

    fun Instant.startOfWeek(): Instant {
        return LocalDate.from(atDefaultZone())
            .with(TemporalAdjusters.previousOrSame(WeekFields.SUNDAY_START.firstDayOfWeek))
            .atStartOfDayInDefaultZone()
            .toInstant()
    }

    fun Instant.startOfMonth(): Instant {
        return LocalDate.from(atDefaultZone())
            .with(TemporalAdjusters.firstDayOfMonth())
            .atStartOfDayInDefaultZone()
            .toInstant()
    }

    fun Instant.monthsAgo(months: Long): Instant {
        return LocalDate.from(atDefaultZone())
            .minus(months, ChronoUnit.MONTHS)
            .atStartOfDayInDefaultZone()
            .toInstant()
    }

    fun Instant.startOfYear(): Instant {
        return LocalDate.from(atDefaultZone())
            .with(TemporalAdjusters.firstDayOfYear())
            .atStartOfDayInDefaultZone()
            .toInstant()
    }

    fun durationDuringMeasurementPeriod(
        start: Instant,
        end: Instant,
        measurementStart: Instant,
        measurementEnd: Instant
    ): Duration {
        return Duration.between(
            listOf(start, measurementStart).max(),
            listOf(end, measurementEnd).min()
        )
    }
}