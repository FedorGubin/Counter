package com.example.counter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.counter.databinding.TextItemBinding

class MyAdapter : RecyclerView.Adapter<MyHolder>() {

    private val items = mutableListOf<String>()

    fun addNewItem(newItem: String) {
        items.add(newItem)
        notifyItemInserted(items.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun insertNewItems(newItems: List<String>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.text_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(items[position])
    }
}

class MyHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: TextItemBinding = TextItemBinding.bind(view)

    fun bind(item: String) {
        binding.title.text = item
    }
}