package app.elephantintheroom.ijustwantsomethingtodo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(
    tableName = "time_spent",
    foreignKeys = [
        ForeignKey(
            entity = ThingToDoEntity::class,
            parentColumns = ["thing_to_do_id"],
            childColumns = ["time_spent_thing_to_do_id"]
        )
    ]
)
data class TimeSpentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "time_spent_id")
    val id: Long? = null,

    @ColumnInfo(name = "time_spent_thing_to_do_id")
    val thingToDoId: Long,

    @ColumnInfo(name = "time_spent_started")
    val started: Instant,

    @ColumnInfo(name = "time_spent_ended")
    val ended: Instant? = null,
)
