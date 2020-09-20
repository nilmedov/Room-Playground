package com.example.room.trigger.ui.logs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.trigger.data.log.Log
import kotlinx.android.synthetic.main.item_log.view.*

class LogsAdapter : RecyclerView.Adapter<LogsAdapter.LogsViewHolder>() {

    private val items = mutableListOf<Log>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        return LogsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_log, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    fun setItems(items: List<Log>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class LogsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val item = items[position]
            itemView.txt_timestamp.text = item.timestamp
            itemView.txt_operation.text = item.operation
            itemView.txt_payload.text = item.payload
        }
    }

}