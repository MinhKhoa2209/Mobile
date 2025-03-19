package com.example.unscrambleapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.example.unscrambleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setContentView(binding.root)

        viewModel.gameFinished.observe(this) { isFinished ->
            if (isFinished) {
                Toast.makeText(this, "Game Over! Your score: ${viewModel.scoreLiveData.value}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
