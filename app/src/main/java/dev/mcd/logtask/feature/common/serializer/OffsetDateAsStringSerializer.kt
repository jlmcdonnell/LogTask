package dev.mcd.logtask.feature.common.serializer

import dev.mcd.logtask.feature.common.mapper.OffsetDateTimeMapper
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.OffsetDateTime

object OffsetDateAsStringSerializer : KSerializer<OffsetDateTime> {

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("OffsetDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: OffsetDateTime) {
        encoder.encodeString(OffsetDateTimeMapper.serialize(value))
    }

    override fun deserialize(decoder: Decoder): OffsetDateTime {
        return OffsetDateTimeMapper.deserialize(decoder.decodeString())
    }
}
