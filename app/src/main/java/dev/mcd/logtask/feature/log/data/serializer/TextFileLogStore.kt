package dev.mcd.logtask.feature.log.data.serializer

import dev.mcd.logtask.feature.log.di.ForLogStore
import dev.mcd.logtask.feature.log.domain.LogItem
import dev.mcd.logtask.feature.log.domain.LogStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Inject

/**
 * Implements a plain text file representation of the log as serialized by LogItemSerializer
 * Each log item is appended to a new line in the text file in JSON format.
 * JSON Array is not used as the whole file would need to decoded to JSON when appending each line
 */
class TextFileLogStore @Inject constructor(
    @ForLogStore
    private val logFile: File,
    private val json: Json,
    @ForLogStore
    private val dispatcher: CoroutineDispatcher,
) : LogStore {

    private val logFlow = MutableSharedFlow<List<LogItem>>()

    override suspend fun all(): List<LogItem> {
        return withContext(dispatcher) {
            getOrCreateFile().readLines().map { line ->
                json.decodeFromString<LogItemSerializer>(line).toDomain()
            }
        }
    }

    override suspend fun allAsFlow(): Flow<List<LogItem>> {
        return flow {
            emit(all())
            emitAll(logFlow)
        }
    }

    override suspend fun append(item: LogItem) {
        withContext(dispatcher) {
            getOrCreateFile().run {
                appendText(json.encodeToString(item.serializer()))
                appendText("\n")
            }
            updateLogFlow()
        }
    }

    override suspend fun clear() {
        withContext(dispatcher) {
            logFile.delete()
            updateLogFlow()
        }
    }

    private suspend fun updateLogFlow() {
        logFlow.emit(all())
    }

    private fun getOrCreateFile(): File {
        return logFile.apply {
            if (!exists()) {
                createNewFile()
            }
        }
    }
}
