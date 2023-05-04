package dev.mcd.logtask.feature.log.data.serializer

import dev.mcd.logtask.feature.common.serializer.OffsetDateAsStringSerializer
import dev.mcd.logtask.feature.log.domain.LogItem
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

@Serializable
data class LogItemSerializer(
    @Serializable(OffsetDateAsStringSerializer::class)
    val createdAt: OffsetDateTime,
    val text: String,
)

fun LogItemSerializer.toDomain() = LogItem(
    createdAt = createdAt,
    text = text,
)

fun LogItem.serializer() = LogItemSerializer(
    createdAt = createdAt,
    text = text,
)
