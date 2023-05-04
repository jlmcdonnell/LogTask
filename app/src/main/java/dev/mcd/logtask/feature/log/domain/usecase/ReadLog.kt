package dev.mcd.logtask.feature.log.domain.usecase

import dev.mcd.logtask.feature.log.domain.LogItem
import kotlinx.coroutines.flow.Flow

interface ReadLog {
    suspend operator fun invoke(): Flow<List<LogItem>>
}
