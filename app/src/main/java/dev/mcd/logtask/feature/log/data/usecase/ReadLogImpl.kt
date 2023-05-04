package dev.mcd.logtask.feature.log.data.usecase

import dev.mcd.logtask.feature.log.domain.LogItem
import dev.mcd.logtask.feature.log.domain.LogStore
import dev.mcd.logtask.feature.log.domain.usecase.ReadLog
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadLogImpl @Inject constructor(
    private val logStore: LogStore,
) : ReadLog {

    override suspend fun invoke(): Flow<List<LogItem>> {
        return logStore.allAsFlow()
    }
}
