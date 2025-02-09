package app.elephantintheroom.ijustwantsomethingtodo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "things_to_do")
data class ThingToDoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "thing_to_do_id")
    val id: Long? = null,

    @ColumnInfo(name = "thing_to_do_name")
    val name: String,
)

data class ThingToDoIncludingActiveTimeSpentEntity(
    @Embedded
    val thingToDoEntity: ThingToDoEntity,
    @Embedded
    val activeTimeSpentEntity: TimeSpentEntity?
)
