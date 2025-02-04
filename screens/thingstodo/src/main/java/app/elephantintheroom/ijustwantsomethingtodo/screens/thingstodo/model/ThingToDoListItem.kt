package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThingToDoListItem(val id: Int, val name: String) : Parcelable
