package app.elephantintheroom.ijustwantsomethingtodo.core.domain.activethingtodo

import app.elephantintheroom.ijustwantsomethingtodo.core.domain.common.TransactionProvider
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoWithTimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import kotlin.test.assertEquals

class OneActiveThingToDoUseCaseTest {
    private lateinit var oneActiveThingToDoUseCase: OneActiveThingToDoUseCase
    private lateinit var transactionProvider: FakeTransactionProvider
    private lateinit var thingToDoRepository: FakeThingToDoRepository
    private lateinit var timeSpentRepository: FakeTimeSpentRepository
    private lateinit var clock: Clock

    @Before
    fun setUp() {
        transactionProvider = FakeTransactionProvider()
        timeSpentRepository = FakeTimeSpentRepository()
        thingToDoRepository = FakeThingToDoRepository(timeSpentRepository)
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())
        oneActiveThingToDoUseCase = OneActiveThingToDoUseCase(
            thingToDoRepository,
            timeSpentRepository,
            transactionProvider,
            clock,
        )
    }

    @Test
    fun testStartingThingsToDo() = runTest {
        oneActiveThingToDoUseCase.startThingToDo(
            thingToDoRepository.thingsToDo.first { it.id == 1L },
        ) { false }

        assertEquals(
            mutableListOf(
                TimeSpent(1, 1, Instant.now(clock), null)
            ),
            timeSpentRepository.allRecordedTimeSpent,
        )

        oneActiveThingToDoUseCase.startThingToDo(
            thingToDoRepository.thingsToDo.first { it.id == 2L },
        ) { false }

        assertEquals(
            mutableListOf(
                TimeSpent(1, 1, Instant.now(clock), null)
            ),
            timeSpentRepository.allRecordedTimeSpent,
        )
    }

    @Test
    fun testHandlingActiveThingsToDo() = runTest {
        var timesHandlerCalled = 0

        oneActiveThingToDoUseCase.startThingToDo(
            thingToDoRepository.thingsToDo.first { it.id == 1L },
        ) { false }
        assertEquals(
            0,
            timesHandlerCalled,
        )

        oneActiveThingToDoUseCase.startThingToDo(
            thingToDoRepository.thingsToDo.first { it.id == 2L },
        ) {
            timesHandlerCalled++
            false
        }
        assertEquals(
            1,
            timesHandlerCalled,
        )
        assertEquals(
            mutableListOf(
                TimeSpent(1, 1, Instant.now(clock), null)
            ),
            timeSpentRepository.allRecordedTimeSpent,
        )

        oneActiveThingToDoUseCase.startThingToDo(
            thingToDoRepository.thingsToDo.first { it.id == 2L },
        ) {
            timesHandlerCalled++
            timeSpentRepository.allRecordedTimeSpent.replaceAll { it.copy(ended = Instant.now(clock)) }
            true
        }
        assertEquals(
            2,
            timesHandlerCalled,
        )
        assertEquals(
            mutableListOf(
                TimeSpent(1, 1, Instant.now(clock), Instant.now(clock)),
                TimeSpent(2, 2, Instant.now(clock), null)
            ),
            timeSpentRepository.allRecordedTimeSpent,
        )
    }
}

class FakeTransactionProvider : TransactionProvider {
    override suspend fun <R> runAsTransaction(block: suspend () -> R): R {
        return block.invoke()
    }
}

class FakeThingToDoRepository(
    private val timeSpentRepository: FakeTimeSpentRepository
) : ThingToDoRepository {
    val thingsToDo: List<ThingToDo> = listOf(
        ThingToDo(1, ""),
        ThingToDo(2, ""),
    )

    override fun getAllThingsToDo(): Flow<List<ThingToDo>> {
        TODO("Not yet implemented")
    }

    override fun getAllThingsToDoWithTimeSpent(): Flow<List<ThingToDoWithTimeSpent>> {
        TODO("Not yet implemented")
    }

    override fun getAllActiveThingsToDo(): Flow<List<ActiveThingToDo>> =
        listOf(thingsToDo.mapNotNull { thingToDo ->
            val activeTimeSpent = timeSpentRepository.allRecordedTimeSpent.firstOrNull {
                it.thingToDoId == thingToDo.id
            }
            activeTimeSpent?.let { ActiveThingToDo(thingToDo, activeTimeSpent) }
        }).asFlow()

    override suspend fun addThingToDo(thingToDo: ThingToDo): ThingToDo {
        TODO("Not yet implemented")
    }
}

class FakeTimeSpentRepository : TimeSpentRepository {
    val allRecordedTimeSpent: MutableList<TimeSpent> = mutableListOf()

    override suspend fun recordTimeSpent(timeSpent: TimeSpent): TimeSpent {
        val recordedTimeSpent: TimeSpent =
            timeSpent.copy(id = (allRecordedTimeSpent.size + 1).toLong())
        allRecordedTimeSpent.addLast(recordedTimeSpent)
        return recordedTimeSpent
    }
}
