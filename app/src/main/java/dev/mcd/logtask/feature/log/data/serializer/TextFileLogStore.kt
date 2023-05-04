package dev.mcd.logtask.feature.log.data.serializer

import dev.mcd.logtask.feature.log.di.LogFile
import dev.mcd.logtask.feature.log.domain.LogItem
import dev.mcd.logtask.feature.log.domain.LogStore
import kotlinx.coroutines.CoroutineDispatcher
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
    @LogFile
    private val logFile: File,
    private val json: Json,
    private val dispatcher: CoroutineDispatcher,
) : LogStore {

    override suspend fun all(): List<LogItem> {
        return withContext(dispatcher) {
            getOrCreateFile().readLines().map { line ->
                json.decodeFromString<LogItemSerializer>(line).toDomain()
            }
        }
    }

    override suspend fun append(item: LogItem) {
        withContext(dispatcher) {
            getOrCreateFile().run {
                appendText(json.encodeToString(item.serializer()))
                appendText("\n")
            }
        }
    }

    override suspend fun clear() {
        withContext(dispatcher) {
            logFile.delete()
        }
    }

    private fun getOrCreateFile(): File {
        return logFile.apply {
            if (!exists()) {
                createNewFile()
            }
        }
    }
}
