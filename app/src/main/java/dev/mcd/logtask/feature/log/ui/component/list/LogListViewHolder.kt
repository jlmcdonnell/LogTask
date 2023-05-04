package dev.mcd.logtask.feature.log.ui.component.list

import androidx.recyclerview.widget.RecyclerView
import dev.mcd.logtask.databinding.LogListItemBinding
import dev.mcd.logtask.feature.log.ui.LogItemUIModel

class LogListViewHolder(
    private val binding: LogListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LogItemUIModel) {
        binding.logItem = item
    }
}
