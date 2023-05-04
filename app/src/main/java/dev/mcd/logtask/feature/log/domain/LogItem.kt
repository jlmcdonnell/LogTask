package dev.mcd.logtask.feature.log.domain

import java.time.OffsetDateTime

data class LogItem(
    val createdAt: OffsetDateTime,
    val text: String,
)
