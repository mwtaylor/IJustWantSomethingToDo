package app.elephantintheroom.ijustwantsomethingtodo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.Duration
import java.time.Instant

interface ThingToDo : Parcelable {
    val id: Long?
    val name: String
}

@Parcelize
data class ExistingThingToDo(
    override val id: Long,
    override val name: String,
) : ThingToDo, Parcelable

@Parcelize
data class NewThingToDo(
    override val id: Long? = null,
    override val name: String,
) : ThingToDo, Parcelable

interface ThingToDoWithTimeSpent : Parcelable {
    val thingToDo: ExistingThingToDo
    val concludedTimeSpent: List<TimeSpent>

    fun totalConcludedDuration(): Duration {
        return concludedTimeSpent.fold(Duration.ZERO) { acc, timeSpent ->
            acc.plus(Duration.between(timeSpent.started, timeSpent.ended))
        }
    }
}

@Parcelize
data class InactiveThingToDo(
    override val thingToDo: ExistingThingToDo,
    override val concludedTimeSpent: List<TimeSpent>,
) : ThingToDoWithTimeSpent, Parcelable

@Parcelize
data class ActiveThingToDo(
    override val thingToDo: ExistingThingToDo,
    val activeTimeSpent: TimeSpent,
    override val concludedTimeSpent: List<TimeSpent>,
) : ThingToDoWithTimeSpent, Parcelable {
    fun activeDuration(now: Instant): Duration = Duration.between(activeTimeSpent.started, now)
}
