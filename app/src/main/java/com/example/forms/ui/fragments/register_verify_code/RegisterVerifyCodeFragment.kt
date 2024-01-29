package com.example.forms.ui.fragments.register_verify_code

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.forms.R
import com.example.forms.databinding.FragmentRegisterVerifyCodeBinding
import com.example.forms.ui.activities.register.RegisterListener
import com.example.forms.util.OnTextChangeWatcher

class RegisterVerifyCodeFragment : Fragment(R.layout.fragment_register_verify_code) {

    private lateinit var binding: FragmentRegisterVerifyCodeBinding
    private lateinit var viewModel: RegisterVerifyCodeViewModel
    private var registerActivityListener: RegisterListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterVerifyCodeBinding.bind(view)
        viewModel = ViewModelProvider(this)[RegisterVerifyCodeViewModel::class.java]

        with(binding) {
            registerVerifycodeBtnBack.setOnClickListener(handleGoBack)
            registerVerifycodeEditVerifycode.addTextChangedListener(watcher)
            registerVerifycodeBtnNext.setOnClickListener(handleNext)
        }
    }

    private val handleNext = View.OnClickListener {
        val code = binding.registerVerifycodeEditVerifycode.text.toString();
        viewModel.sendCodeVerification(code)
        viewModel.sendCodeVerificationSuccess.observe(this){
            if(it) {
                handleSuccessVerifyCode()
            } else {
                binding.registerVerifycodeInputVerifycode.error = getString(R.string.invalid_code)
            }
        }
    }

    private fun handleSuccessVerifyCode(){
        registerActivityListener?.navToPassword()
    }

    private val watcher = OnTextChangeWatcher { code ->
        viewModel.verifyCodeFormat(code)
        viewModel.verifyCodeFormat.observe(this) {
            binding.registerVerifycodeBtnNext.isEnabled = it
            binding.registerVerifycodeInputVerifycode.error =
                if (!it) getString(R.string.invalid_verify_code_format) else null
        }
    }

    private val handleGoBack = View.OnClickListener {
        registerActivityListener?.goBack()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RegisterListener) {
            registerActivityListener = context
        }
    }
}