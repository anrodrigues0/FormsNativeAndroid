package com.example.forms.ui.activities.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.forms.R
import com.example.forms.databinding.ActivityRegisterBinding
import com.example.forms.ui.fragments.register_email.RegisterEmailFragment
import com.example.forms.ui.fragments.register_information.RegisterInformationFragment
import com.example.forms.ui.fragments.register_password.RegisterPasswordFragment
import com.example.forms.ui.fragments.register_photo.RegisterPhotoFragment
import com.example.forms.ui.fragments.register_success.RegisterSuccess
import com.example.forms.ui.fragments.register_verify_code.RegisterVerifyCodeFragment
import com.example.forms.ui.fragments.select_photo.SelectPhotoFragment

class RegisterActivity : AppCompatActivity(), RegisterListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentManager(RegisterEmailFragment())
    }


    private fun fragmentManager(fragment: Fragment) {
        if (supportFragmentManager.findFragmentById(R.id.register_fragments_host) == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.register_fragments_host, fragment)
                    .commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.register_fragments_host, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun goBack() {
        if(supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun navToVerifyCode() {
        fragmentManager(RegisterVerifyCodeFragment())
    }

    override fun navToPassword() {
        fragmentManager(RegisterPasswordFragment())
    }

    override fun navToInformation() {
        fragmentManager(RegisterInformationFragment())
    }

    override fun navToPhoto() {
        fragmentManager(RegisterPhotoFragment())
    }


    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        fragmentManager(SelectPhotoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(SelectPhotoFragment.KEY_IMG_URL, it)
            }
        })
    }

    override fun navToSelectPhoto() {
        getContent.launch("image/*")
    }

    override fun navToSuccess() {
        fragmentManager(RegisterSuccess())
    }
}