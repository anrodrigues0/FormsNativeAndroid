package com.example.forms.ui.activities.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forms.R
import com.example.forms.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}