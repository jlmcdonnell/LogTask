package dev.mcd.logtask.feature.log.ui.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import dev.mcd.logtask.R
import dev.mcd.logtask.feature.log.ui.LogItemUIModel

class LogListView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private val adapter = LogListAdapter()
    private val listView by lazy { findViewById<RecyclerView>(R.id.listView) }

    init {
        inflate(context, R.layout.log_list_view, this)
        listView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setLogItems(items: List<LogItemUIModel>) {
        if (adapter.items != items) {
            // For purpose of task and time constraints, will not use item diffing here
            adapter.items = items
            adapter.notifyDataSetChanged()
        }
    }
}
