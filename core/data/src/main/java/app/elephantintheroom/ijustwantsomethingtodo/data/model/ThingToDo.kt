package app.elephantintheroom.ijustwantsomethingtodo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThingToDo(
    val id: Long?,
    val name: String,
) : Parcelable

@Parcelize
data class ThingToDoIncludingActiveTimeSpent(
    val thingToDo: ThingToDo,
    val activeTimeSpent: TimeSpent?,
) : Parcelable

@Parcelize
data class ActiveThingToDo(
    val thingToDo: ThingToDo,
    val activeTimeSpent: TimeSpent,
) : Parcelable {
    fun toOptionalActiveTimeSpent(): ThingToDoIncludingActiveTimeSpent {
        return ThingToDoIncludingActiveTimeSpent(thingToDo, activeTimeSpent)
    }
}
