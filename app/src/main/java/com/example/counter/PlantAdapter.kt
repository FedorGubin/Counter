package com.example.counter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.counter.databinding.PlantItemBinding


class PlantAdapter : RecyclerView.Adapter<PlantAdapter.PlantHolder>() {
    private val plantList = ArrayList<Plant>()

    class PlantHolder(item: View) : ViewHolder(item) {
        private val binding = PlantItemBinding.bind(item)
        fun bind(plant: Plant) {
            binding.apply {
                im.setImageResource(plant.imageId)
                tvTitle.text = plant.title
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.plant_item, parent, false)
        return PlantHolder(view)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        holder.bind(plantList[position])
    }

    fun addPlant(plant: Plant) {
        plantList.add(plant)
        notifyItemInserted(plantList.size - 1)
    }

}
