package com.example.counter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.counter.databinding.MainActivityBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val viewModel by viewModels<MainViewModel>()

    private fun setCont() {
        binding.counter.text = viewModel.counter.toString()
    }

    private fun setEnabledButtons() {
        binding.goNextScreen.isEnabled = viewModel.isButtonsEnabled
        binding.newColor.isEnabled = viewModel.isButtonsEnabled
        binding.minus.isVisible = viewModel.isButtonsEnabled
        binding.plus.isVisible = viewModel.isButtonsEnabled
        binding.counter.isVisible = viewModel.isButtonsEnabled
        binding.hide.text = if (viewModel.isButtonsEnabled) {
            getString(R.string.hide)
        } else getString(R.string.visible)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.test()
        viewModel.test3()

        setEnabledButtons()
        binding.hide.setOnClickListener {
            viewModel.isButtonsEnabled = !viewModel.isButtonsEnabled
            setEnabledButtons()
        }

        binding.newColor.setOnClickListener {
            val randomColor = randomColor()

            binding.counter.setTextColor(randomColor)
        }

        setCont()
        binding.plus.setOnClickListener {
            viewModel.test4()
            viewModel.counter++
            setCont()
        }

        binding.minus.setOnClickListener {
            viewModel.test2()
            viewModel.counter--
            setCont()
        }
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        binding.goNextScreen.setOnClickListener {
            startActivity(intent)
        }
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
