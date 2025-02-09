package app.elephantintheroom.ijustwantsomethingtodo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThingToDoIncludingActiveTimeSpent(
    val thingToDo: ThingToDo,
    val activeTimeSpent: TimeSpent?,
) : Parcelable
