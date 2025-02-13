package app.elephantintheroom.ijustwantsomethingtodo.core.domain.common

interface TransactionProvider {
    suspend fun <R> runAsTransaction(block: suspend () -> R): R
}
