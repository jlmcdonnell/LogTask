package dev.mcd.logtask.feature.log.domain

interface LogStore {
    suspend fun all(): List<LogItem>
    suspend fun append(item: LogItem)
    suspend fun clear()
}
