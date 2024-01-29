package com.example.forms.ui.fragments.register_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forms.model.ValidationForms


class RegisterPasswordViewModel : ViewModel() {
    private val _isPasswordAvailable = MutableLiveData<ValidationForms>()
    val isPasswordAvailable: LiveData<ValidationForms> get() = _isPasswordAvailable

    fun verifyIsPasswordAvailable(password: String, confirmPassword: String) {
        val confirmPasswordVerification = confirmPassword == password

        val forcePassword =
            Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*()_+{}\\[\\]:;<>,.?~\\\\/-]).{8,}\$").matches(
                password
            )

        if (!forcePassword) {
            _isPasswordAvailable.value =
                ValidationForms(false, "Password is not strong")
            return;
        }

        if (!confirmPasswordVerification) {
            _isPasswordAvailable.value =
                ValidationForms(false, "Password and confirm password is not the same")
            return;
        }

        _isPasswordAvailable.value =
            ValidationForms(true)
    }
}