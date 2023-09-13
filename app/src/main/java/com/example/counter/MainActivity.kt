package com.example.counter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.counter.databinding.MainActivityBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val viewModel: MyViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.isButtonsEnabledLiveData.observe(this, Observer { isEnabled ->
            binding.goNextScreen.isEnabled = isEnabled
            binding.newColor.isEnabled = isEnabled
            binding.plus.isEnabled = isEnabled
            binding.minus.isEnabled = isEnabled

            binding.hide.text = if (isEnabled)
                getString(R.string.hide) else getString(R.string.visibility)
        })

        viewModel.newColorLiveData.observe(this, Observer { newColors ->
            binding.counter.setTextColor(newColors)
        })
        binding.newColor.setOnClickListener {
            val randomColor = randomColor()
            binding.counter.setTextColor(randomColor)
            viewModel.toggleButtonColor(randomColor)
        }





        viewModel.counterLiveData.observe(this, Observer { count ->
            binding.counter.text = count.toString()
        })
        binding.hide.setOnClickListener {
            viewModel.toggleButtonState()
        }
        binding.plus.setOnClickListener {
            viewModel.incrementCounter()
        }

        binding.minus.setOnClickListener {
            viewModel.decrementCounter()
        }
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        binding.goNextScreen.setOnClickListener {
            startActivity(intent)
        }
    }
    fun randomColor(): Int {
        val r = Random.nextInt(255)
        val g = Random.nextInt(255)
        val b = Random.nextInt(255)
        return Color.rgb(r, g, b)
    }
}











