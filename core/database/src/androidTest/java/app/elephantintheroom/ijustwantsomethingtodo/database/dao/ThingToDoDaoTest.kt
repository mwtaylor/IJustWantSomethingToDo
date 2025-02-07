package app.elephantintheroom.ijustwantsomethingtodo.database.dao

import app.elephantintheroom.ijustwantsomethingtodo.database.DatabaseTestBase
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContains

internal class ThingToDoDaoTest : DatabaseTestBase() {
    @Test
    fun testBasicInsertAndGet() = runTest {
        thingToDoDao.insert(
            ThingToDoEntity(name = "first thing to do"),
        )

        val thingToDoEntity = thingToDoDao.get(1L)
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

        val secondThingToDoEntity = thingToDoDao.get(2L)
        assertEquals(
            2L,
            secondThingToDoEntity.id,
        )
        assertContains(
            secondThingToDoEntity.name,
            "second",
        )

        val thirdThingToDoEntity = thingToDoDao.get(3L)
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
}