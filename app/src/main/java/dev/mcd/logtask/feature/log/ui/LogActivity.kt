package dev.mcd.logtask.feature.log.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import dev.mcd.logtask.R
import dev.mcd.logtask.databinding.LogActivityBinding
import dev.mcd.logtask.feature.log.ui.LogViewModel.SideEffect
import dev.mcd.logtask.feature.log.ui.LogViewModel.SideEffect.ClearLogInput
import dev.mcd.logtask.feature.log.ui.LogViewModel.SideEffect.ShowGenericError
import dev.mcd.logtask.feature.log.ui.LogViewModel.State
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class LogActivity : ComponentActivity() {

    private val viewModel by viewModels<LogViewModel>()
    private lateinit var binding: LogActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.log_activity)

        viewModel.observe(
            state = ::render,
            sideEffect = ::handleSideEffect,
            lifecycleOwner = this,
        )

        binding.setupUI()
    }

    private fun render(state: State) {
        binding.logListView.setLogItems(state.logItems)
    }

    private fun handleSideEffect(effect: SideEffect) {
        when (effect) {
            ClearLogInput -> binding.logInputView.clear()
            ShowGenericError -> showGenericError()
        }
    }

    private fun showGenericError() {
        Toast.makeText(this, R.string.generic_error, Toast.LENGTH_SHORT).show()
    }

    private fun LogActivityBinding.setupUI() {
        logInputView.run {
            onOkClicked = {
                viewModel.onOkClicked()
            }
            onTextChanged = { text ->
                viewModel.onInputUpdated(text)
            }
        }
    }
}
