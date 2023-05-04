package dev.mcd.logtask.feature.log.domain

import java.time.OffsetDateTime

interface LogTimeProvider {
    fun provideTime(): OffsetDateTime
}
