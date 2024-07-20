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

val previewThingsToDo = listOf(
    ThingToDo(id = 1, name = "Work"),
    ThingToDo(id = 2, name = "Play"),
    ThingToDo(id = 3, name = "Clean"),
)
