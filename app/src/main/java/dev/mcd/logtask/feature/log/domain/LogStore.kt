package dev.mcd.logtask.feature.log.domain

import kotlinx.coroutines.flow.Flow

interface LogStore {
    suspend fun all(): List<LogItem>
    suspend fun allAsFlow(): Flow<List<LogItem>>
    suspend fun append(item: LogItem)
    suspend fun clear()
}
