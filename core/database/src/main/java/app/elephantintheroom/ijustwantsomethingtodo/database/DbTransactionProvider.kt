package app.elephantintheroom.ijustwantsomethingtodo.database

import androidx.room.RoomDatabase
import androidx.room.withTransaction
import app.elephantintheroom.ijustwantsomethingtodo.core.domain.common.TransactionProvider

class DbTransactionProvider(private val database: RoomDatabase) : TransactionProvider {
    override suspend fun <R> runAsTransaction(block: suspend () -> R): R {
        return database.withTransaction(block)
    }
}
