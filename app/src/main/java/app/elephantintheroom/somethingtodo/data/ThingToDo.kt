package app.elephantintheroom.somethingtodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity
data class ThingToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)

val thingsToDo = mapOf(
    Pair(ThingToDo(name = "Work"), TimeSpent(thingToDoId = 0, started = Instant.now())),
    Pair(ThingToDo(name = "Play"), null),
    Pair(ThingToDo(name = "Clean"), null),
    Pair(ThingToDo(name = "Rest"), null),
)
