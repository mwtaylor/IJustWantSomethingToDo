package app.elephantintheroom.somethingtodo

import app.elephantintheroom.somethingtodo.TimeUtilities.startOfDay
import app.elephantintheroom.somethingtodo.TimeUtilities.startOfMonth
import app.elephantintheroom.somethingtodo.TimeUtilities.startOfWeek
import app.elephantintheroom.somethingtodo.TimeUtilities.startOfYear
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.TimeZone

class TimeUtilitiesTest {
    private fun instantFromDateTime(dateTime: LocalDateTime): Instant {
        return dateTime.toInstant(ZoneOffset.UTC)
    }

    @Test
    fun startOfDayTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        assertThat(
            instantFromDateTime(
                LocalDateTime.of(2024, 7, 23, 13, 30)
            ).startOfDay()
        )
            .isEqualTo(
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 23, 0, 0)
                )
            )
    }

    @Test
    fun startOfWeekTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        assertThat(
            instantFromDateTime(
                LocalDateTime.of(2024, 7, 23, 13, 30)
            ).startOfWeek()
        )
            .isEqualTo(
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 21, 0, 0)
                )
            )
    }

    @Test
    fun startOfMonthTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        assertThat(
            instantFromDateTime(
                LocalDateTime.of(2024, 7, 23, 13, 30)
            ).startOfMonth()
        )
            .isEqualTo(
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 1, 0, 0)
                )
            )
    }

    @Test
    fun startOfYearTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        assertThat(
            instantFromDateTime(
                LocalDateTime.of(2024, 7, 23, 13, 30)
            ).startOfYear()
        )
            .isEqualTo(
                instantFromDateTime(
                    LocalDateTime.of(2024, 1, 1, 0, 0)
                )
            )
    }

    @Test
    fun durationDuringMeasurementPeriodTest() {
        // Start and end within measurement period
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        assertThat(
            TimeUtilities.durationDuringMeasurementPeriod(
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 23, 3, 0)
                ),
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 23, 5, 0)
                ),
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 23, 0, 0)
                ),
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 24, 0, 0)
                )
            )
        )
            .isEqualTo(
                Duration.ofHours(2)
            )

        // Start outside measurement period
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        assertThat(
            TimeUtilities.durationDuringMeasurementPeriod(
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 22, 23, 0)
                ),
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 23, 5, 0)
                ),
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 23, 0, 0)
                ),
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 24, 0, 0)
                )
            )
        )
            .isEqualTo(
                Duration.ofHours(5)
            )

        // End outside measurement period
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        assertThat(
            TimeUtilities.durationDuringMeasurementPeriod(
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 23, 3, 0)
                ),
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 24, 1, 0)
                ),
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 23, 0, 0)
                ),
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 24, 0, 0)
                )
            )
        )
            .isEqualTo(
                Duration.ofHours(21)
            )

        // Start and end outside measurement period
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        assertThat(
            TimeUtilities.durationDuringMeasurementPeriod(
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 22, 23, 0)
                ),
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 24, 1, 0)
                ),
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 23, 0, 0)
                ),
                instantFromDateTime(
                    LocalDateTime.of(2024, 7, 24, 0, 0)
                )
            )
        )
            .isEqualTo(
                Duration.ofHours(24)
            )
    }
}