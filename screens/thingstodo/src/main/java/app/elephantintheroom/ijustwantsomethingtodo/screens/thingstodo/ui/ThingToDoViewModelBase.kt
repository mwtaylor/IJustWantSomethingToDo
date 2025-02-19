package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import app.elephantintheroom.ijustwantsomethingtodo.core.domain.activethingtodo.OneActiveThingToDoUseCase
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import java.time.Clock
import java.time.Instant

interface ThingToDoViewModelBase {
    suspend fun _startSpendingTime(
        thingToDo: ThingToDo,
        oneActiveThingToDoUseCase: OneActiveThingToDoUseCase,
    ) {
        oneActiveThingToDoUseCase.startThingToDo(thingToDo) { false }
    }

    suspend fun _stopSpendingTime(
        timeSpent: TimeSpent,
        timeSpentRepository: TimeSpentRepository,
        clock: Clock,
    ) {
        timeSpentRepository.recordTimeSpent(timeSpent.copy(ended = Instant.now(clock)))
    }
}