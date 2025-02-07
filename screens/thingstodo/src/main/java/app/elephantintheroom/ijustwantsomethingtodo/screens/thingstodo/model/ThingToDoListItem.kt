package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface ThingToDoListItem

@Parcelize
data class ExistingThingToDoListItem(val id: Long, val name: String) : Parcelable, ThingToDoListItem

@Parcelize
data object NewThingToDoListItem : Parcelable, ThingToDoListItem
