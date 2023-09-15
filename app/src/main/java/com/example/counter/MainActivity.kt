package com.example.counter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.counter.databinding.MainActivityBinding
import com.example.counter.databinding.RecyclerViewItemBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val viewModel by viewModels<MainViewModel>()

    private val listRandomString = listOf(
        "onCreate",
        "listRandomString",
        "viewModel",
        "viewModels",
        "MainViewModel",
        "MainActivity",
        "AppCompatActivity",
        "MainActivityBinding",
        "binding",
        "root",
        "override",
        "fun",
        "Bundle",
        "LinearLayoutManager"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyAdapter()

        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.adapter = adapter
        binding.buttonAdd.setOnClickListener {
            adapter.updateData(
                newItem = listRandomString[Random.nextInt(listRandomString.size - 1)]
            )
        }
    }
}

class MyAdapter : RecyclerView.Adapter<MyHolder>() {

    private val items: MutableList<String> = mutableListOf()

    fun updateData(newItem: String) {
        if (items.size == 10) {
            val tmp = items[4]
            items[4] = items[8]
            items[8] = tmp
            notifyItemMoved(4, 8)
        } else {
            items.add(newItem)
            notifyItemInserted(items.size - 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.title.text = items[position]
    }
}

class MyHolder constructor(
    private val inflater: LayoutInflater,
    val binding: RecyclerViewItemBinding = RecyclerViewItemBinding.inflate(inflater)
) : RecyclerView.ViewHolder(binding.root)
