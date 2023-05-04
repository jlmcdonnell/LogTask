package dev.mcd.logtask.feature.log.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import dev.mcd.logtask.R

class LogInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val okButton by lazy { findViewById<View>(R.id.okButton) }
    private val inputText by lazy { findViewById<TextInputEditText>(R.id.logInputText) }

    var onTextChanged: ((String) -> Unit)? = null
    var onOkClicked: (() -> Unit)? = null

    init {
        inflate(context, R.layout.log_input_view, this)

        inputText.doOnTextChanged { text, _, _, _ ->
            onTextChanged?.invoke(text?.toString() ?: "")
        }

        okButton.setOnClickListener {
            onOkClicked?.invoke()
        }

        inputText.setOnEditorActionListener { _, _, _ ->
            onOkClicked?.invoke()
            true
        }
    }

    fun clear() {
        inputText.setText("")
    }
}
