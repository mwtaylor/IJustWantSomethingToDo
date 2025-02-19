package app.elephantintheroom.ijustwantsomethingtodo.database.dao

import app.elephantintheroom.ijustwantsomethingtodo.database.DatabaseTestBase
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.TimeSpentEntity
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.Instant
import kotlin.test.assertContains

internal class ThingToDoDaoTest : DatabaseTestBase() {
    @Test
    fun testBasicInsertAndGet() = runTest {
        thingToDoDao.insert(
            ThingToDoEntity(name = "first thing to do"),
        )

        val thingToDoEntity = thingToDoDao.getWithTimeSpent(1L).first().thingToDoEntity
        assertEquals(
            1L,
            thingToDoEntity.id,
        )
        assertContains(
            thingToDoEntity.name,
            "first",
        )

        thingToDoDao.insert(listOf(
            ThingToDoEntity(name = "second thing to do"),
            ThingToDoEntity(name = "third thing to do"),
        ))

        val secondThingToDoEntity = thingToDoDao.getWithTimeSpent(2L).first().thingToDoEntity
        assertEquals(
            2L,
            secondThingToDoEntity.id,
        )
        assertContains(
            secondThingToDoEntity.name,
            "second",
        )

        val thirdThingToDoEntity = thingToDoDao.getWithTimeSpent(3L).first().thingToDoEntity
        assertEquals(
            3L,
            thirdThingToDoEntity.id,
        )
        assertContains(
            thirdThingToDoEntity.name,
            "third",
        )
    }

    @Test
    fun testGetAll() = runTest {
        thingToDoDao.insert(listOf(
            ThingToDoEntity(name = "first thing to do"),
            ThingToDoEntity(name = "second thing to do"),
        ))

        val thingToDoEntities = thingToDoDao.getAll().first()

        assertEquals(
            listOf(1L, 2L),
            thingToDoEntities.map { it.id },
        )
    }

    @Test
    fun testGetAllWithTimeSpent() = runTest {
        thingToDoDao.insert(listOf(
            ThingToDoEntity(name = "first thing to do"),
            ThingToDoEntity(name = "second thing to do"),
            ThingToDoEntity(name = "third thing to do"),
        ))
        timeSpentDao.upsert(
            TimeSpentEntity(thingToDoId = 2, started = Instant.EPOCH)
        )
        timeSpentDao.upsert(
            TimeSpentEntity(thingToDoId = 3, started = Instant.EPOCH, ended = Instant.now())
        )
        timeSpentDao.upsert(
            TimeSpentEntity(thingToDoId = 3, started = Instant.EPOCH, ended = Instant.now())
        )

        val thingToDoWithTimeSpentEntities = thingToDoDao.getAllWithTimeSpent().first()

        assertEquals(
            listOf(1L, 2L, 3L),
            thingToDoWithTimeSpentEntities.map { it.thingToDoEntity.id },
        )
        assertEquals(
            emptyList(),
            thingToDoWithTimeSpentEntities[0].timeSpent.map { timeSpent -> timeSpent.id },
        )
        assertEquals(
            listOf(1L),
            thingToDoWithTimeSpentEntities[1].timeSpent.map { timeSpent -> timeSpent.id },
        )
        assertEquals(
            listOf(2L, 3L),
            thingToDoWithTimeSpentEntities[2].timeSpent.map { timeSpent -> timeSpent.id },
        )
        assertEquals(
            listOf(null, 1L, null),
            thingToDoWithTimeSpentEntities.map {
                it.timeSpent.singleOrNull { timeSpent -> timeSpent.ended == null }?.id
            },
        )
    }
}