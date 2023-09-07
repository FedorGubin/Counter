package com.example.counter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.counter.databinding.MainActivityBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var isButtonsEnabled = true
        binding.hide.setOnClickListener {
            if (isButtonsEnabled) {
                binding.goNextScreen.isEnabled = false
                binding.newColor.isEnabled = false
                binding.minus.isVisible = false
                binding.plus.isVisible = false
                binding.counter.isVisible = false

                binding.hide.text = getString(R.string.visible)

            } else {
                binding.goNextScreen.isEnabled = true
                binding.newColor.isEnabled = true
                binding.plus.isVisible = true
                binding.minus.isVisible = true
                binding.counter.isVisible = true


                binding.hide.text = getString(R.string.hide)

            }
            isButtonsEnabled = !isButtonsEnabled
        }
        fun randomColor(): Int {
            val r = Random.nextInt(256)
            val g = Random.nextInt(256)
            val b = Random.nextInt(256)
            return Color.rgb(r, g, b)
        }
        binding.newColor.setOnClickListener {
            val randomColor = randomColor()

            binding.counter.setTextColor(randomColor)
        }

        var counter = 0

        binding.counter.text = counter.toString()


        binding.plus.setOnClickListener {
            counter++
            binding.counter.text = counter.toString()
        }

        binding.minus.setOnClickListener {
            counter--
            binding.counter.text = counter.toString()
        }
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        binding.goNextScreen.setOnClickListener {
            startActivity(intent)
        }
    }
}
