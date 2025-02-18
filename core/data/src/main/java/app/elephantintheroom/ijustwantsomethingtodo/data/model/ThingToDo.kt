package app.elephantintheroom.ijustwantsomethingtodo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.Duration
import java.time.Instant

@Parcelize
data class ThingToDo(
    val id: Long?,
    val name: String,
) : Parcelable

interface ThingToDoWithTimeSpent : Parcelable {
    val thingToDo: ThingToDo
    val concludedTimeSpent: List<TimeSpent>

    fun totalConcludedDuration(): Duration {
        return concludedTimeSpent.fold(Duration.ZERO) { acc, timeSpent ->
            acc.plus(Duration.between(timeSpent.started, timeSpent.ended))
        }
    }
}

@Parcelize
data class InactiveThingToDo(
    override val thingToDo: ThingToDo,
    override val concludedTimeSpent: List<TimeSpent>,
) : ThingToDoWithTimeSpent, Parcelable

@Parcelize
data class ActiveThingToDo(
    override val thingToDo: ThingToDo,
    val activeTimeSpent: TimeSpent,
    override val concludedTimeSpent: List<TimeSpent>,
) : ThingToDoWithTimeSpent, Parcelable {
    fun activeDuration(now: Instant): Duration = Duration.between(activeTimeSpent.started, now)
}
