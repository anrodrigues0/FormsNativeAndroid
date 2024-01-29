package com.example.forms.ui.fragments.register_password

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.forms.R
import com.example.forms.databinding.FragmentRegisterPasswordBinding
import com.example.forms.ui.activities.register.RegisterListener
import com.example.forms.ui.activities.register.RegisterSharedViewModel
import com.example.forms.util.OnTextChangeWatcher

class RegisterPasswordFragment : Fragment(R.layout.fragment_register_password) {

    private lateinit var binding: FragmentRegisterPasswordBinding
    private lateinit var viewModel: RegisterPasswordViewModel
    private var registerActivityListener: RegisterListener? = null;
    private val sharedViewModel by activityViewModels<RegisterSharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPasswordBinding.bind(view)
        viewModel = ViewModelProvider(this)[RegisterPasswordViewModel::class.java]

        with(binding) {
            registerPassBtnBack.setOnClickListener(handleGoBack)
            registerPassEditConfirmPass.addTextChangedListener(watcherPassword)
            registerPassEditPassword.addTextChangedListener(watcherPassword)
            registerPassBtnNext.setOnClickListener(handleNext)
        }
    }


    private val handleGoBack = View.OnClickListener {
        registerActivityListener?.goBack()

    }


    private val watcherPassword = OnTextChangeWatcher {
        val password = binding.registerPassEditPassword.text.toString()
        val confirmPass = binding.registerPassEditConfirmPass.text.toString()

        viewModel.verifyIsPasswordAvailable(password, confirmPass)
        viewModel.isPasswordAvailable.observe(this) {
            binding.registerPassStrongPassIndicator.checkPasswordStrong(password)
            if (it.isSuccess) {
                binding.registerPassBtnNext.isEnabled = true
                binding.registerPassTextError.text = ""
            } else {
                binding.registerPassBtnNext.isEnabled = false
                binding.registerPassTextError.text = it.msg
            }
        }
    }

    private val handleNext = View.OnClickListener {
        registerActivityListener?.navToInformation()
        sharedViewModel.setPassword(binding.registerPassEditConfirmPass.text.toString())
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RegisterListener) {
            registerActivityListener = context
        }
    }
}
