package com.example.forms.ui.activities.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.forms.databinding.ActivityMainBinding
import com.example.forms.di.DependencyInjection
import com.example.forms.ui.activities.login.LoginActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = DependencyInjection.UserRepository()
        val authRepository = DependencyInjection.AuthRepository()
        viewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(repository, authRepository)
        )[HomeViewModel(repository, authRepository)::class.java]

        viewModel.getUser()
        viewModel.userInformation.observe(this)  {
            binding.homeTxtName.text = it.name
            binding.homeImgUser.setImageURI(it.imgURL)
        }

        binding.homeBtnLogOut.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}