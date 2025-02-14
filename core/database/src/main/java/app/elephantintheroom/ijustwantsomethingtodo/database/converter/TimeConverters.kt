package app.elephantintheroom.ijustwantsomethingtodo.database.converter

import androidx.room.TypeConverter
import java.time.Instant

class TimeConverters {
    @TypeConverter
    fun instantToEpochSecond(instant: Instant?) = instant?.epochSecond

    @TypeConverter
    fun epochSecondToInstant(seconds: Long?) = seconds?.let { Instant.ofEpochSecond(it) }
}