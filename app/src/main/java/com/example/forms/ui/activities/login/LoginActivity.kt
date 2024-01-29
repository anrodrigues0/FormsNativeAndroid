package com.example.forms.ui.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.forms.R
import com.example.forms.databinding.ActivityLoginBinding
import com.example.forms.di.DependencyInjection
import com.example.forms.ui.activities.home.HomeActivity
import com.example.forms.ui.activities.register.RegisterActivity
import com.example.forms.util.OnTextChangeWatcher

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = DependencyInjection.AuthRepository()
        viewModel = ViewModelProvider(this, LoginViewModelFactory(repository))[LoginViewModel(repository)::class.java]

        with(binding) {
            loginBtnLogin.setOnClickListener(handleLogin)
            loginEditEmail.addTextChangedListener(watcher)
            loginBtnSingUp.setOnClickListener(handleRegister)
        }
    }


    private val handleRegister = View.OnClickListener {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private val handleLogin = View.OnClickListener {
        val emailText = binding.loginEditEmail.text.toString()
        val passwordText = binding.loginEditPassword.text.toString()
        viewModel.login(emailText, passwordText)
        viewModel.isSuccessLogin.observe(this) {
            if(it.isSuccess){
               startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                binding.loginPassTextError.text = it.msg
            }
        }
    }

    private fun handleInvalidEmail(isValid: Boolean) {
        if (isValid) {
            binding.loginBtnLogin.isEnabled = true
            binding.loginInputEmail.error = null
        } else {
            binding.loginBtnLogin.isEnabled = false
            binding.loginInputEmail.error = getString(R.string.invalid_email)
        }
    }

    private val watcher = OnTextChangeWatcher() { email ->
        viewModel.verifyEmail(email)
        viewModel.isEmailInvalidFormat.observe(this@LoginActivity) { isEmailValid ->
            handleInvalidEmail(isEmailValid)
        }
    }
}
