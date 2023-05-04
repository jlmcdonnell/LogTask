package dev.mcd.logtask.feature.common.mapper

import io.kotest.matchers.shouldBe
import org.junit.Test
import java.time.OffsetDateTime
import java.time.ZoneOffset

class OffsetDateTimeMapperTest {

    @Test
    fun `Deserialize date string`() {
        // Given
        val dateString = "2023-05-04T09:29:57.123456789Z"

        // When
        val actual = OffsetDateTimeMapper.deserialize(dateString)

        // Then
        val expected = OffsetDateTime.of(
            2023, 5, 4, 9, 29, 57, 123456789, ZoneOffset.UTC,
        )

        actual shouldBe expected
    }

    @Test
    fun `Serialize offset date time`() {
        // Given
        val dateTime = OffsetDateTime.of(2023, 5, 4, 9, 40, 15, 123456789, ZoneOffset.UTC)

        // When
        val actual = OffsetDateTimeMapper.serialize(dateTime)

        // Then
        val expected = "2023-05-04T09:40:15.123456789Z"

        actual shouldBe expected
    }

}
