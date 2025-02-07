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
            parentColumns = ["id"],
            childColumns = ["thing_to_do_id"]
        )
    ]
)
data class TimeSpentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "thing_to_do_id")
    val thingToDoId: Long,

    @ColumnInfo(name = "started")
    val started: Instant,

    @ColumnInfo(name = "ended")
    val ended: Instant? = null,
)
