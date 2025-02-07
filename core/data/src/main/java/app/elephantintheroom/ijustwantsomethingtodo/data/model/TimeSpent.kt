package app.elephantintheroom.ijustwantsomethingtodo.data.model

import java.time.Instant

data class TimeSpent(
    val id: Long?,
    val thingToDoId: Long,
    val started: Instant,
    val ended: Instant?
)
