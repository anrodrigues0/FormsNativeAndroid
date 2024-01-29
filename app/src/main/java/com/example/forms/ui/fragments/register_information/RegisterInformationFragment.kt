package com.example.forms.ui.fragments.register_information

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.forms.R
import com.example.forms.databinding.FragmentRegisterInformationBinding
import com.example.forms.ui.activities.register.RegisterListener
import com.example.forms.ui.activities.register.RegisterSharedViewModel
import com.example.forms.util.OnTextChangeWatcher


class RegisterInformationFragment: Fragment(R.layout.fragment_register_information) {

    private lateinit var binding: FragmentRegisterInformationBinding
    private lateinit var viewModel: RegisterInformationViewModel
    private var registerActivityListener: RegisterListener? = null
    private val sharedViewModel by activityViewModels<RegisterSharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterInformationBinding.bind(view)
        viewModel = ViewModelProvider(this)[RegisterInformationViewModel::class.java]

        with(binding){
            registerInfoBackButton.setOnClickListener(handleGoBack)
            registerInfoEditName.addTextChangedListener(watcher)
            registerInfoBtnNext.setOnClickListener(handleNextButton)
        }
    }

    private val watcher = OnTextChangeWatcher {
        viewModel.verifyNameValid(it)
        viewModel.isValidName.observe(this){ valid ->
            binding.registerInfoBtnNext.isEnabled = valid
        }
    }

    private val handleNextButton = View.OnClickListener {
        registerActivityListener?.navToPhoto()
        sharedViewModel.setName(binding.registerInfoEditName.text.toString())
    }

    private val handleGoBack = View.OnClickListener {
        registerActivityListener?.goBack()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is RegisterListener) {
            registerActivityListener = context
        }
    }
}