package app.elephantintheroom.somethingtodo.data

data class ThingToDo(val name: String)

val thingsToDo = listOf(
    ThingToDo(name = "Work"),
    ThingToDo(name = "Play"),
    ThingToDo(name = "Clean"),
    ThingToDo(name = "Rest"),
)
