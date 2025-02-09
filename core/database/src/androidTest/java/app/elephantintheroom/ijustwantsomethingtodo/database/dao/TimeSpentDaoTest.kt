package app.elephantintheroom.ijustwantsomethingtodo.database.dao

import app.elephantintheroom.ijustwantsomethingtodo.database.DatabaseTestBase
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.TimeSpentEntity
import kotlinx.coroutines.test.runTest
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class TimeSpentDaoTest : DatabaseTestBase() {
    @Test
    fun testBasicInsertAndGet() = runTest {
        thingToDoDao.insert(
            ThingToDoEntity(name = "")
        )

        val id = timeSpentDao.record(
            TimeSpentEntity(thingToDoId = 1, started = Instant.now())
        )
        assertEquals(
            1,
            id,
        )

        val timeSpentEntity = timeSpentDao.get(id)
        assertEquals(
            1,
            timeSpentEntity.id,
        )
    }

    @Test
    fun testInsertAndRetrieveActiveThingToDo() = runTest {
        thingToDoDao.insert(
            ThingToDoEntity(name = "")
        )

        timeSpentDao.record(
            TimeSpentEntity(thingToDoId = 1, started = Instant.now(), ended = Instant.now())
        )
        timeSpentDao.record(
            TimeSpentEntity(thingToDoId = 1, started = Instant.now())
        )

        assertEquals(
            2L,
            timeSpentDao.getActiveForThingToDo(1L)?.id,
        )

        assertNull(
            timeSpentDao.getActiveForThingToDo(1L)?.ended,
        )
    }

    @Test
    fun testUpdateActiveTimeSpentWithEndTime() = runTest {
        thingToDoDao.insert(
            ThingToDoEntity(name = "")
        )

        timeSpentDao.record(
            TimeSpentEntity(
                id = 1,
                thingToDoId = 1,
                started = Instant.EPOCH,
            )
        )
        timeSpentDao.record(
            TimeSpentEntity(
                id = 1,
                thingToDoId = 1,
                started = Instant.EPOCH,
                ended = Instant.ofEpochSecond(1)
            )
        )

        assertNull(
            timeSpentDao.getActiveForThingToDo(1)
        )
    }
}