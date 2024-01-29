package com.example.forms.ui.fragments.register_verify_code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterVerifyCodeViewModel:ViewModel() {
    private val _verifyCodeFormat = MutableLiveData<Boolean>()
    val verifyCodeFormat:LiveData<Boolean> get() = _verifyCodeFormat

    private val _sendCodeVerificationSuccess = MutableLiveData<Boolean>()
    val sendCodeVerificationSuccess: LiveData<Boolean> get() = _sendCodeVerificationSuccess

    fun verifyCodeFormat(code:String){
        _verifyCodeFormat.value = code.length == 6
    }

    fun sendCodeVerification(code:String){
        _sendCodeVerificationSuccess.value = code == "123456"
    }
}