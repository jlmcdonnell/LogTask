package dev.mcd.logtask.feature.log.ui.component.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.mcd.logtask.R

class LogListAdapter : RecyclerView.Adapter<LogListViewHolder>() {

    override fun getItemCount(): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogListViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.log_list_item, parent, false)
            .let(::LogListViewHolder)
    }

    override fun onBindViewHolder(holder: LogListViewHolder, position: Int) {
    }
}
