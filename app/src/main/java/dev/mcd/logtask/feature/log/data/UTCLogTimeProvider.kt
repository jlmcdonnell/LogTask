package dev.mcd.logtask.feature.log.data

import dev.mcd.logtask.feature.log.domain.LogTimeProvider
import java.time.OffsetDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class UTCLogTimeProvider @Inject constructor() : LogTimeProvider {

    override fun provideTime(): OffsetDateTime {
        return OffsetDateTime.now(ZoneOffset.UTC)
    }
}
