package app.elephantintheroom.ijustwantsomethingtodo.data.model

import android.os.Parcelable
import java.time.Instant
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimeSpent(
    val id: Long?,
    val thingToDoId: Long,
    val started: Instant,
    val ended: Instant?
) : Parcelable
