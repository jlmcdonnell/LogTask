package dev.mcd.logtask.feature.log.ui.component.list

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.DiffUtil
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

    fun setLogItems(items: List<LogItemUIModel>) {
        val result = DiffUtil.calculateDiff(
            object : DiffUtil.Callback() {
                override fun getOldListSize() = adapter.itemCount

                override fun getNewListSize() = items.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return adapter.items[oldItemPosition] == items[newItemPosition]
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return areItemsTheSame(oldItemPosition, newItemPosition)
                }
            },
        )
        result.dispatchUpdatesTo(adapter)
        adapter.items = items

        if (items.isNotEmpty()) {
            listView.scrollToPosition(items.size - 1)
        }
    }
}
