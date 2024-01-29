package com.example.forms.ui.fragments.register_information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterInformationViewModel:ViewModel() {
    private val _isValidName = MutableLiveData<Boolean>()
    val isValidName:LiveData<Boolean> get() = _isValidName

    fun verifyNameValid(name:String){
        _isValidName.value = name.length > 4
    }
}