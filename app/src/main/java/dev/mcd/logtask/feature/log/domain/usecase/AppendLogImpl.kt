package dev.mcd.logtask.feature.log.domain.usecase

import dev.mcd.logtask.feature.log.domain.LogItem
import dev.mcd.logtask.feature.log.domain.LogStore
import dev.mcd.logtask.feature.log.domain.LogTimeProvider
import javax.inject.Inject

class AppendLogImpl @Inject constructor(
    private val logStore: LogStore,
    private val timeProvider: LogTimeProvider,
) : AppendLog {

    override suspend operator fun invoke(text: String) {
        logStore.append(
            item = LogItem(
                createdAt = timeProvider.provideTime(),
                text = text,
            ),
        )
    }
}
