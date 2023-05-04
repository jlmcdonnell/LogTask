package dev.mcd.logtask.feature.log.ui.component.list

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import dev.mcd.logtask.R

class LogListView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attributeSet, defStyleAttr) {

    init {
        inflate(context, R.layout.log_list_view, this)
    }
}
