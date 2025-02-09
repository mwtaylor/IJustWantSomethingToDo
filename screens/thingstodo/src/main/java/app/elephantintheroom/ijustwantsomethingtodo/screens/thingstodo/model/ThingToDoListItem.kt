package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model

import android.os.Parcelable
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import kotlinx.parcelize.Parcelize

interface ThingToDoListItem : Parcelable

@Parcelize
data class ExistingThingToDoListItem(
    val thingToDo: ThingToDo,
    val activeTimeSpent: TimeSpent?,
) : ThingToDoListItem

@Parcelize
data object NewThingToDoListItem : ThingToDoListItem
