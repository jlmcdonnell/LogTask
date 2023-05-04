package dev.mcd.logtask.feature.log.ui.component.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.mcd.logtask.R
import dev.mcd.logtask.databinding.LogListItemBinding
import dev.mcd.logtask.feature.log.ui.LogItemUIModel

class LogListAdapter : RecyclerView.Adapter<LogListViewHolder>() {

    var items: List<LogItemUIModel> = emptyList()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DataBindingUtil.inflate<LogListItemBinding>(
            inflater,
            R.layout.log_list_item,
            parent,
            false,
        ).let(::LogListViewHolder)
    }

    override fun onBindViewHolder(holder: LogListViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
