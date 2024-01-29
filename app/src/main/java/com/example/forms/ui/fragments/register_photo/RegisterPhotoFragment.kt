package com.example.forms.ui.fragments.register_photo

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.forms.R
import com.example.forms.constants.FragmentResultsKeys
import com.example.forms.databinding.FragmentRegisterPhotoBinding
import com.example.forms.di.DependencyInjection
import com.example.forms.ui.activities.register.RegisterListener
import com.example.forms.ui.activities.register.RegisterSharedViewModel
import com.example.forms.ui.fragments.select_photo.SelectPhotoFragment
import java.io.File

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    private var registerActivityListener: RegisterListener? = null;
    private lateinit var binding: FragmentRegisterPhotoBinding
    private lateinit var viewModel: RegisterPhotoFragmentViewModel
    private val sharedViewModel by activityViewModels<RegisterSharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterPhotoBinding.bind(view)

        val repository = DependencyInjection.AuthRepository()
        viewModel = ViewModelProvider(
            this,
            RegisterPhotoFragmentViewModelFactory(repository)
        )[RegisterPhotoFragmentViewModel(repository)::class.java]
        with(binding) {
            registerPhotoBtnBack.setOnClickListener(handleGoBack)
            registerPhotoBtnAddPhoto.setOnClickListener(handleAddPhoto)
            registerPhotoBtnFinish.setOnClickListener(handleFinish)
        }
    }

    private fun loadImageUri() {

        setFragmentResultListener(FragmentResultsKeys.REGISTER_KEYS.CROP_IMG_SAVE) { requestKey, bundle ->
            val uri = bundle.getParcelable<Uri>(SelectPhotoFragment.KEY_IMG_URL)
            if (uri != null) {
                sharedViewModel.setImageURI(uri)
                binding.registerPhotoImageUser.setImageURI(Uri.fromFile(File(uri.toString())));
            }
        }
    }

    private val handleFinish = View.OnClickListener {
        sharedViewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                viewModel.registerUser(user = it)
            }
        }

        viewModel.isRegister.observe(viewLifecycleOwner) {
            if (it) {
                registerActivityListener?.navToSuccess()
            }
        }
    }

    private val handleGoBack = View.OnClickListener {
        registerActivityListener?.goBack()
    }

    private val handleAddPhoto = View.OnClickListener {
        registerActivityListener?.navToSelectPhoto()
    }


    override fun onResume() {
        loadImageUri()
        super.onResume()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RegisterListener) {
            registerActivityListener = context
        }
    }
}