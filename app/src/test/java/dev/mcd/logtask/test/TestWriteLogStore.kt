package dev.mcd.logtask.test

import dev.mcd.logtask.feature.log.domain.LogItem
import dev.mcd.logtask.feature.log.domain.LogStore

class TestWriteLogStore : LogStore {

    val testInput = mutableListOf<LogItem>()

    override suspend fun all() = throw NotImplementedError()

    override suspend fun allAsFlow() = throw NotImplementedError()

    override suspend fun append(item: LogItem) {
        testInput.add(item)
    }

    override suspend fun clear() {
        testInput.clear()
    }
}
