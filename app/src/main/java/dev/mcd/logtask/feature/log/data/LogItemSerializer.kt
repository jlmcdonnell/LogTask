package dev.mcd.logtask.feature.log.data

import dev.mcd.logtask.feature.common.mapper.OffsetDateTimeMapper
import dev.mcd.logtask.feature.log.domain.LogItem

data class LogItemSerializer(
    val createdAt: String,
    val text: String,
)

fun LogItemSerializer.toDomain() = LogItem(
    createdAt = OffsetDateTimeMapper.deserialize(createdAt),
    text = text,
)

fun LogItem.serializer() = LogItemSerializer(
    createdAt = OffsetDateTimeMapper.serialize(createdAt),
    text = text,
)
