package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model

import android.os.Parcelable
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoWithTimeSpent
import kotlinx.parcelize.Parcelize

interface ThingToDoListItem : Parcelable

@Parcelize
data class ExistingThingToDoListItem(
    val thingToDoWithTimeSpent: ThingToDoWithTimeSpent,
) : ThingToDoListItem

@Parcelize
data object NewThingToDoListItem : ThingToDoListItem
