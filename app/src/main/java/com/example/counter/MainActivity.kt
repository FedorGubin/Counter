package com.example.counter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.counter.databinding.MainActivityBinding
import com.example.counter.databinding.SecondActivityBinding
import com.example.counter.ui.theme.SecondActivity
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
                binding.plus.isEnabled = false
                binding.minus.isEnabled = false

                binding.hide.text = "Visibility"

            } else {
                binding.goNextScreen.isEnabled = true
                binding.newColor.isEnabled = true
                binding.plus.isEnabled = true
                binding.minus.isEnabled = true

                binding.hide.text = "Hide"

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


            binding.plus.setOnClickListener {
                counter++
                binding.counter.text = counter.toString()
            }

            binding.minus.setOnClickListener {
                counter--
                binding.counter.text = counter.toString()
            }
        var intent = Intent(this@MainActivity, SecondActivity::class.java)
        binding.goNextScreen.setOnClickListener {
            startActivity(intent)
        }
        }
    }











