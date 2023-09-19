package com.example.counter

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.counter.databinding.MainActivityBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    private val newAdapter = MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun init() {
        val sharedPref = getSharedPreferences(CACHE_NAME, MODE_PRIVATE)

        binding.rcView.layoutManager = GridLayoutManager(this, 3)
        binding.rcView.adapter = newAdapter
        binding.buttonAdd.setOnClickListener {
            onClickButton(sharedPref)
        }
        insertCachedValues(sharedPref)
//        binding.buttonAdd.setOnClickListener {
//            if (index > 4) index = 0
//            val plant = Plant(plantList[index], "Plant ${index + 1}")
//            adapter.addPlant(plant)
//            index++
//        }
    }

    private fun insertCachedValues(sharedPref: SharedPreferences) {
        val cachedStrings: List<String> = sharedPref
            .getStringSet(STRING, null)?.toList() ?: emptyList()
        newAdapter.insertNewItems(cachedStrings)
    }

    private fun onClickButton(sharedPref: SharedPreferences) {
        val generateString: String = getRandomString(Random.nextInt(1, 10))
        newAdapter.addNewItem(generateString)
        val insertString = sharedPref.getStringSet(STRING, null)?.let { cachedSet ->
            val tmpSet = mutableSetOf<String>()
            tmpSet.addAll(cachedSet)
            tmpSet.add(generateString)
            tmpSet
        } ?: setOf(generateString)
        sharedPref.edit().putStringSet(STRING, insertString).apply()
    }

    companion object {
        private const val CACHE_NAME = "CACHE"
        private const val STRING = "STRING_ARG"
    }
}
