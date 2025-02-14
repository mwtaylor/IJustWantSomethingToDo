package app.elephantintheroom.ijustwantsomethingtodo.core.domain.activethingtodo

import app.elephantintheroom.ijustwantsomethingtodo.core.domain.common.TransactionProvider
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import java.time.Instant
import kotlinx.coroutines.flow.first
import java.time.Clock

class OneActiveThingToDoUseCase(
    private val thingToDoRepository: ThingToDoRepository,
    private val timeSpentRepository: TimeSpentRepository,
    private val transactionProvider: TransactionProvider,
    private val clock: Clock,
) {
    suspend fun startThingToDo(
        thingToDo: ThingToDo,
        handleExistingActiveThingsToDo: (List<ActiveThingToDo>) -> Boolean,
    ) {
        transactionProvider.runAsTransaction {
            val activeThingsToDo = thingToDoRepository.getAllActiveThingsToDo().first()
            val shouldProceed = if (activeThingsToDo.isNotEmpty()) {
                handleExistingActiveThingsToDo(activeThingsToDo)
            } else {
                true
            }

            if (shouldProceed) {
                timeSpentRepository.recordTimeSpent(
                    TimeSpent(
                        null,
                        thingToDo.id!!,
                        Instant.now(clock),
                        null
                    )
                )
            }
        }
    }
}
