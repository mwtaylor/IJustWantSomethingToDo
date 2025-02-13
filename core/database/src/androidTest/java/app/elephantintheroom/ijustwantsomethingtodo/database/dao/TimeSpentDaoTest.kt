package app.elephantintheroom.ijustwantsomethingtodo.database.dao

import app.elephantintheroom.ijustwantsomethingtodo.database.DatabaseTestBase
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.TimeSpentEntity
import kotlinx.coroutines.test.runTest
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

internal class TimeSpentDaoTest : DatabaseTestBase() {
    @Test
    fun testBasicInsertAndGet() = runTest {
        thingToDoDao.insert(
            ThingToDoEntity(name = "")
        )

        val newTimeSpentEntityId = timeSpentDao.upsert(
            TimeSpentEntity(thingToDoId = 1, started = Instant.EPOCH)
        )
        assertEquals(
            1,
            newTimeSpentEntityId,
        )

        val timeSpentEntity = timeSpentDao.get(1)
        assertEquals(
            1,
            timeSpentEntity.id,
        )
        assertEquals(
            0,
            timeSpentEntity.started.epochSecond,
        )
    }

    @Test
    fun testUpdateActiveTimeSpentWithEndTime() = runTest {
        thingToDoDao.insert(
            ThingToDoEntity(name = "")
        )

        assertEquals(
            1,
            timeSpentDao.upsert(
                TimeSpentEntity(
                    id = 1,
                    thingToDoId = 1,
                    started = Instant.EPOCH,
                )
            )
        )

        assertEquals(
            -1,
            timeSpentDao.upsert(
                TimeSpentEntity(
                    id = 1,
                    thingToDoId = 1,
                    started = Instant.EPOCH,
                    ended = Instant.ofEpochSecond(1)
                )
            )
        )

        assertEquals(
            1L,
            timeSpentDao.get(1).ended?.epochSecond,
        )
    }
}
