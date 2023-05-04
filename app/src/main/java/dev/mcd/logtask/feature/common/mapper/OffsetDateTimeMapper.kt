package dev.mcd.logtask.feature.common.mapper

import java.time.OffsetDateTime

object OffsetDateTimeMapper {
    fun serialize(dateTime: OffsetDateTime): String {
        return dateTime.toString()
    }

    fun deserialize(dateTime: String): OffsetDateTime {
        return OffsetDateTime.parse(dateTime)
    }
}
