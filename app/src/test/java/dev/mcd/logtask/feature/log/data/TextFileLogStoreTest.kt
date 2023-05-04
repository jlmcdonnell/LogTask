package dev.mcd.logtask.feature.log.data

import app.cash.turbine.test
import dev.mcd.logtask.feature.log.data.serializer.TextFileLogStore
import dev.mcd.logtask.feature.log.domain.LogItem
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.time.OffsetDateTime

class TextFileLogStoreTest {

    @get:Rule
    val tempFolder: TemporaryFolder = TemporaryFolder()

    @Test
    fun `Append log item`() = runBlocking<Unit> {
        // Given
        val store = givenLogStore()
        val item = LogItem(
            createdAt = OffsetDateTime.MAX,
            text = "Hello, World!",
        )

        // When
        store.append(item)

        // Then
        store.all().first() shouldBe item
    }

    @Test
    fun `Append multiple items`() = runBlocking<Unit> {
        // Given
        val store = givenLogStore()
        val items = listOf(
            LogItem(OffsetDateTime.MIN, "1"),
            LogItem(OffsetDateTime.MAX, "2"),
        )

        // When
        items.forEach {
            store.append(it)
        }

        // Then
        store.all() shouldBe items
    }

    @Test
    fun `Clear store`() = runBlocking<Unit> {
        // Given
        val store = givenLogStore()

        // When
        store.append(LogItem(OffsetDateTime.MIN, "1"))
        store.clear()

        // Then
        store.all() shouldBe emptyList()
    }

    @Test
    fun `Log flow updates after appending`() = runBlocking {
        // Given
        val store = givenLogStore()
        val items = listOf(LogItem(OffsetDateTime.MIN, "1"))

        store.allAsFlow().test {
            // When
            awaitItem() shouldBe emptyList()
            items.forEach { store.append(it) }

            // Then
            awaitItem() shouldBe items
        }
    }

    @Test
    fun `Log flow updates after clearing`() = runBlocking {
        // Given
        val store = givenLogStore()
        val items = listOf(LogItem(OffsetDateTime.MIN, "1"))

        store.allAsFlow().test {
            // When
            items.forEach { store.append(it) }
            awaitItem() shouldBe emptyList()
            awaitItem() shouldBe items
            store.clear()

            // Then
            awaitItem() shouldBe emptyList()
        }
    }

    @Test
    fun `Calling Log flow emits initial state`() = runBlocking {
        // Given
        val store = givenLogStore()
        val items = listOf(LogItem(OffsetDateTime.MIN, "1"))
        items.forEach { store.append(it) }

        // When
        store.allAsFlow().test {
            // Then
            awaitItem() shouldBe items
        }
    }

    private fun givenLogStore(
        file: File = tempFolder.newFile(),
        json: Json = Json,
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
    ) = TextFileLogStore(
        logFile = file,
        json = json,
        dispatcher = dispatcher,
    )
}
