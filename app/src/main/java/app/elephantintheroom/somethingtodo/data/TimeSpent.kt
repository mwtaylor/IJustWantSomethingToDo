package app.elephantintheroom.somethingtodo.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ThingToDo::class,
            parentColumns = ["id"],
            childColumns = ["thingToDoId"]
        )
    ]
)
data class TimeSpent(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val thingToDoId: Int,
    val started: Instant,
    val finished: Instant? = null,
)

val previewTimeSpent= listOf(
    TimeSpent(thingToDoId = previewThingsToDo.single { it.name == "Work" }.id, started = Instant.now().minusSeconds(10))
)
