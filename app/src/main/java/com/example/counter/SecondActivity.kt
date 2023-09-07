package com.example.counter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.counter.databinding.SecondActivityBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: SecondActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}