package com.example.room.trigger.ui.entries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.trigger.data.entry.Entry
import kotlinx.android.synthetic.main.item_entry.view.*

class EntriesAdapter : RecyclerView.Adapter<EntriesAdapter.EntriesViewHolder>() {

    private val items = mutableListOf<Entry>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntriesViewHolder {
        return EntriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_entry, parent, false))
    }

    override fun onBindViewHolder(holder: EntriesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    fun setItems(items: List<Entry>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: Entry) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun removeItem(item: Entry) {
        items.remove(item)
        notifyDataSetChanged()
    }

    inner class EntriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val item = items[position]
            itemView.txt_title.text = item.title
            itemView.txt_timestamp.text = item.timestamp.toString()
        }
    }
}