package dev.mcd.logtask.feature.log.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mcd.logtask.feature.log.domain.LogItem
import dev.mcd.logtask.feature.log.domain.usecase.AppendLog
import dev.mcd.logtask.feature.log.domain.usecase.ReadLog
import dev.mcd.logtask.feature.log.ui.mapper.toUIModel
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LogViewModel @Inject constructor(
    private val readLog: ReadLog,
    private val appendLog: AppendLog,
) : ViewModel(), ContainerHost<LogViewModel.State, LogViewModel.SideEffect> {

    private var logInput: String = ""

    override val container = container<State, SideEffect>(State()) {
        intent {
            readLog().map { items ->
                items.map(LogItem::toUIModel)
            }
        }
    }

    fun onInputUpdated(text: String) {
        logInput = text
    }

    fun onOkPressed() {
        intent {
            runCatching {
                if (logInput.isNotEmpty()) {
                    appendLog(logInput)
                    clearLogInput()
                }
            }.onFailure {
                postSideEffect(SideEffect.ShowGenericError)
            }
        }
    }

    private fun clearLogInput() {
        intent {
            logInput = ""
            postSideEffect(SideEffect.ClearLogInput)
        }
    }

    data class State(
        val logItems: List<LogItemUIModel> = emptyList(),
    )

    sealed interface SideEffect {
        object ShowGenericError : SideEffect
        object ClearLogInput : SideEffect
    }
}
