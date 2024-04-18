package app.elephantintheroom.somethingtodo.data

import androidx.room.TypeConverter
import java.time.Instant

class Converters {
    @TypeConverter
    fun instantToEpochSecond(instant: Instant?) = instant?.epochSecond

    @TypeConverter
    fun epochSecondToInstant(seconds: Long?) = seconds?.let { Instant.ofEpochSecond(it) }
}