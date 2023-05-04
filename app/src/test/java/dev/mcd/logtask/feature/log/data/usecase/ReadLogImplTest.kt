package dev.mcd.logtask.feature.log.data.usecase

import app.cash.turbine.test
import dev.mcd.logtask.feature.log.domain.LogItem
import dev.mcd.logtask.test.TestReadLogStore
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.time.OffsetDateTime

class ReadLogImplTest {

    @Test
    fun `When invoked, Then return flow from Log Store`() = runBlocking {
        // Given
        val items = listOf(LogItem(OffsetDateTime.MIN, "Hello, World!"))
        val store = TestReadLogStore(items)
        val readLog = ReadLogImpl(store)

        // When
        readLog().test {
            // Then
            awaitItem() shouldBe items
            awaitComplete()
        }
    }
}
