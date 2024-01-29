package com.example.forms.ui.fragments.register_email

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterEmailViewModel:ViewModel() {
    private val _isEmailValid = MutableLiveData<Boolean>()
    val isEmailValid:LiveData<Boolean> get() = _isEmailValid

    fun verifyEmailValid(email: String){
        _isEmailValid.value = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}