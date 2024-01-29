package com.example.forms.ui.fragments.register_email

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.forms.R
import com.example.forms.databinding.FragmentRegisterEmailBinding
import com.example.forms.ui.activities.register.RegisterListener
import com.example.forms.ui.activities.register.RegisterSharedViewModel
import com.example.forms.util.OnTextChangeWatcher

class RegisterEmailFragment:Fragment(R.layout.fragment_register_email) {

    private var registerActivityListener:RegisterListener? = null
    private lateinit var viewModel: RegisterEmailViewModel
    private lateinit var binding:FragmentRegisterEmailBinding
    private val sharedViewModel by activityViewModels<RegisterSharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterEmailBinding.bind(view)
        viewModel = ViewModelProvider(this)[RegisterEmailViewModel::class.java]


        with(binding){
            registerEmailBtnBack.setOnClickListener(goBackActivity)
            registerEmailEditEmail.addTextChangedListener(watcher)
            registerEmailBtnNext.setOnClickListener(handleNext)
        }
    }


    private val goBackActivity = View.OnClickListener {
       activity?.finish()
    }

    private val watcher = OnTextChangeWatcher {email ->
        viewModel.verifyEmailValid(email)
        viewModel.isEmailValid.observe(this) {isValid ->
                binding.registerEmailBtnNext.isEnabled = isValid
                binding.registerEmailInputEmail.error = if (!isValid) getString(R.string.invalid_email) else null
        }
    }

    private val handleNext = View.OnClickListener {
        registerActivityListener?.navToVerifyCode()
        sharedViewModel.setEmail(binding.registerEmailEditEmail.text.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is RegisterListener) {
            registerActivityListener = context
        }
    }

}
