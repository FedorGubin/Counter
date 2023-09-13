package com.example.counter

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.counter.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val viewModel: MyViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.isButtonsEnabledLiveData.observe(this) { isEnabled ->
            binding.goNextScreen.isEnabled = isEnabled
            binding.newColor.isEnabled = isEnabled
            binding.plus.isEnabled = isEnabled
            binding.minus.isEnabled = isEnabled

            binding.hide.text = if (isEnabled)
                getString(R.string.hide) else getString(R.string.visibility)
        }

        viewModel.newColorLiveData.observe(this) { newColors ->
            binding.counter.setTextColor(newColors)
        }
        binding.newColor.setOnClickListener {
            viewModel.toggleButtonColor()
        }

        viewModel.counterLiveData.observe(this) { count ->
            binding.counter.text = count.toString()
        }
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
}
