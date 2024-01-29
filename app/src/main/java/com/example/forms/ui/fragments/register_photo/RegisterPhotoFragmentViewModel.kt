package com.example.forms.ui.fragments.register_photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.forms.model.User
import com.example.forms.repository.auth.AuthRepository
import kotlinx.coroutines.launch

class RegisterPhotoFragmentViewModel(private val repository: AuthRepository):ViewModel() {
    private val _isRegister = MutableLiveData<Boolean>()
    val isRegister:LiveData<Boolean> get() =  _isRegister

    fun registerUser(user:User) {
           repository.registerUser(user)
          _isRegister.value = true
    }
}


class RegisterPhotoFragmentViewModelFactory(private val repository: AuthRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterPhotoFragmentViewModel::class.java)) {
            return RegisterPhotoFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}