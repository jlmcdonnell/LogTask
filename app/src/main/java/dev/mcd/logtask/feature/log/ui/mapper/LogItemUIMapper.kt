package dev.mcd.logtask.feature.log.ui.mapper

import dev.mcd.logtask.feature.log.domain.LogItem
import dev.mcd.logtask.feature.log.ui.LogItemUIModel
import dev.mcd.logtask.feature.log.ui.formatter.logTimeUIFormatter

fun LogItem.toUIModel(): LogItemUIModel {
    return LogItemUIModel(
        createdAt = logTimeUIFormatter.format(createdAt),
        text = text,
    )
}
