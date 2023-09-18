package com.example.counter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.counter.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val adapter = PlantAdapter()
    private val plantList = listOf(
        R.drawable.plant1,
        R.drawable.plant2,
        R.drawable.plant3,
        R.drawable.plant4,
        R.drawable.plant5
    )
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.rcView.layoutManager = GridLayoutManager(this, 3)
        binding.rcView.adapter = adapter
        binding.buttonAdd.setOnClickListener {
            if (index > 4) index = 0
            val plant = Plant(plantList[index], "Plant ${index + 1}")
            adapter.addPlant(plant)
            index++
        }
    }

}
