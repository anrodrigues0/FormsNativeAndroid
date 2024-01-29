package com.example.forms.ui.fragments.register_success

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.forms.R
import com.example.forms.databinding.FragmentRegisterSuccessBinding
import com.example.forms.ui.activities.register.RegisterSharedViewModel

class RegisterSuccess:Fragment(R.layout.fragment_register_success) {

    lateinit var binding:FragmentRegisterSuccessBinding
    private val sharedViewModel by activityViewModels<RegisterSharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterSuccessBinding.bind(view)
        binding.registerSuccessBtnLogin.setOnClickListener {
            activity?.finish()
            sharedViewModel.clean()
        }
    }
}