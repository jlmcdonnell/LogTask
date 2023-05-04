package dev.mcd.logtask.test

import dev.mcd.logtask.feature.log.domain.LogItem
import dev.mcd.logtask.feature.log.domain.LogStore
import kotlinx.coroutines.flow.flowOf

class TestReadLogStore(private val logs: List<LogItem>) : LogStore {
    override suspend fun all() = logs

    override suspend fun allAsFlow() = flowOf(logs)

    override suspend fun append(item: LogItem) {
        throw NotImplementedError()
    }

    override suspend fun clear() {
        throw NotImplementedError()
    }
}
