package dev.mcd.logtask.feature.log.ui

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.mcd.logtask.R
import dev.mcd.logtask.feature.log.domain.LogItem
import dev.mcd.logtask.feature.log.domain.LogStore
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltAndroidTest
class LogActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule = ActivityScenarioRule(LogActivity::class.java)

    @Inject
    lateinit var logStore: LogStore

    @Before
    fun init() {
        hiltRule.inject()

        runBlocking {
            logStore.clear()
        }
    }

    @After
    fun tearDown() {
        runBlocking {
            logStore.clear()
        }
    }

    @Test
    fun displayHint() {
        onView(withId(R.id.logInputText))
            .check(matches(withHint(R.string.hint_enter_text)))
    }

    @Test
    fun displayOkButton() {
        onView(withId(R.id.okButton))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayLogItem() {
        // Given
        activityRule.scenario.moveToState(Lifecycle.State.CREATED)

        val item = LogItem(
            createdAt = OffsetDateTime.MIN,
            text = "Hello",
        )

        // When
        runBlocking {
            logStore.append(item)
        }

        activityRule.scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withText("Hello"))
            .check(matches(isDisplayed()))

        onView(withText("00:00"))
            .check(matches(isDisplayed()))
    }
}
