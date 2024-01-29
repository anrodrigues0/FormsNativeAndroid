package com.example.forms.ui.activities.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.forms.model.ValidationForms
import com.example.forms.repository.Callback
import com.example.forms.repository.auth.AuthRepository
import com.example.forms.repository.auth.AuthRepositoryImpl

class LoginViewModel(private val repository: AuthRepository):ViewModel() {
    private val _isEmailInvalidFormat = MutableLiveData<Boolean>()
    val isEmailInvalidFormat: LiveData<Boolean> get() = _isEmailInvalidFormat

    private val _isSuccessLogin = MutableLiveData<ValidationForms>()
    val isSuccessLogin: LiveData<ValidationForms> get() = _isSuccessLogin


    fun login(email:String, password:String){
        repository.login(email,password, object :Callback  {

            override fun onSuccess() {
                _isSuccessLogin.value = ValidationForms(true)
            }

            override fun onError(message: String) {
                _isSuccessLogin.value = ValidationForms(false, message)
            }

        })
    }

    fun verifyEmail(email:String) {
        _isEmailInvalidFormat.value = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

class LoginViewModelFactory(private val repository: AuthRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}