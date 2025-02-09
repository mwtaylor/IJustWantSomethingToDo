package app.elephantintheroom.ijustwantsomethingtodo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThingToDo(
    val id: Long?,
    val name: String,
) : Parcelable
