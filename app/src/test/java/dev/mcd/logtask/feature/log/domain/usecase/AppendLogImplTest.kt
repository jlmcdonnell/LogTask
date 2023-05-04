package dev.mcd.logtask.feature.log.domain.usecase

import dev.mcd.logtask.feature.log.domain.LogTimeProvider
import dev.mcd.logtask.test.TestWriteLogStore
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.time.OffsetDateTime

class AppendLogImplTest {

    @Test
    fun `Given text and a time provider, Append log with correct values`() = runBlocking<Unit> {
        // Given
        val store = TestWriteLogStore()
        val time = OffsetDateTime.now()
        val timeProvider = object : LogTimeProvider {
            override fun provideTime() = time
        }
        val appendLog = AppendLogImpl(store, timeProvider)

        // When
        appendLog("Hello")

        // Then
        store.testInput.first().run {
            createdAt shouldBe time
            text shouldBe "Hello"
        }
    }
}
