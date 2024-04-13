package app.elephantintheroom.somethingtodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thing_to_do")
data class ThingToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)

val thingsToDo = listOf(
    ThingToDo(name = "Work"),
    ThingToDo(name = "Play"),
    ThingToDo(name = "Clean"),
    ThingToDo(name = "Rest"),
)
