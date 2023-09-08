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

    private var counter = 0
    private var isButtonsEnabled = true
    private fun setCont() {
        binding.counter.text = counter.toString()
    }

    private fun setEnabledButtons() {
        binding.goNextScreen.isEnabled = isButtonsEnabled
        binding.newColor.isEnabled = isButtonsEnabled
        binding.minus.isVisible = isButtonsEnabled
        binding.plus.isVisible = isButtonsEnabled
        binding.counter.isVisible = isButtonsEnabled
        binding.hide.text = if (isButtonsEnabled) {
            getString(R.string.hide)
        } else getString(R.string.visible)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setEnabledButtons()
        binding.hide.setOnClickListener {
            isButtonsEnabled = !isButtonsEnabled
            setEnabledButtons()
        }

        binding.newColor.setOnClickListener {
            val randomColor = randomColor()

            binding.counter.setTextColor(randomColor)
        }

        setCont()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(counterKey, counter)
        outState.putBoolean(enabledButtons, isButtonsEnabled)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counter = savedInstanceState.getInt(counterKey)
        setCont()
        isButtonsEnabled = savedInstanceState.getBoolean(enabledButtons)
        setEnabledButtons()
    }

    private fun randomColor(): Int {
        val r = Random.nextInt(256)
        val g = Random.nextInt(256)
        val b = Random.nextInt(256)
        return Color.rgb(r, g, b)
    }

    private val counterKey = "KeyInt"
    private val enabledButtons = "KeyBool"
}
