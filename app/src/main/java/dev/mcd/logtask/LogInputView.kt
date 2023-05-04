package dev.mcd.logtask

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class LogInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.log_input_view, this)
    }

    fun readLinesAsFlow(): Flow<String> {
        return emptyFlow()
    }
}
