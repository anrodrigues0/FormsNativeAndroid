package com.example.forms.ui.fragments.select_photo


import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult

import com.example.forms.R
import com.example.forms.constants.FragmentResultsKeys
import com.example.forms.databinding.FragmentSelectPhotoBinding
import com.example.forms.ui.activities.register.RegisterListener
import com.example.forms.ui.activities.register.RegisterSharedViewModel
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception


class SelectPhotoFragment : Fragment(R.layout.fragment_select_photo) {

    lateinit var binding: FragmentSelectPhotoBinding
    private var registerActivityListener: RegisterListener? = null;
    private val sharedViewModel by activityViewModels<RegisterSharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelectPhotoBinding.bind(view)
        handleCropperImg(arguments?.getParcelable<Uri>(KEY_IMG_URL))


        with(binding) {
            selectPhotoBtnCancel.setOnClickListener(handleCancel)
            selectPhotoBtnSave.setOnClickListener(handleSave)
        }

    }

    private fun handleCropperImg(uri: Uri?) {
        if (uri != null) {
            binding.selectPhotoCropper.apply {
                setAspectRatio(1, 1)
                setFixedAspectRatio(true)
                setImageUriAsync(uri)
            }
        }
    }

    private val handleSave = View.OnClickListener {
        binding.selectPhotoCropper.getCroppedImage()?.let { img ->
            saveImageCropped(img)
        }
    }


    private fun saveImageCropped(croppedBitmap: Bitmap) {
        val path = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File(path, "photo_${System.currentTimeMillis()}.jpeg")

        try {
            FileOutputStream(file).use { out ->
                croppedBitmap.compress(Bitmap.CompressFormat.JPEG, 50, out)
                registerActivityListener?.goBack()
                setFragmentResult(FragmentResultsKeys.REGISTER_KEYS.CROP_IMG_SAVE, Bundle().apply {
                    putParcelable(FragmentResultsKeys.REGISTER_KEYS.CROP_IMG_SAVE, Uri.fromFile(file))
                })

                Toast.makeText(requireContext(), "Save image", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error on save image", Toast.LENGTH_SHORT).show()
        }
    }

    private val handleCancel = View.OnClickListener {
        registerActivityListener?.goBack()
    }


    companion object {
        const val KEY_IMG_URL = "KEY_IMG_URL"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RegisterListener) {
            registerActivityListener = context
        }
    }
}
