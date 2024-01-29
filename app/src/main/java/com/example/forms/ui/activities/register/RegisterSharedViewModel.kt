package com.example.forms.ui.activities.register

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forms.model.User

class RegisterSharedViewModel:ViewModel() {
    private val _user = MutableLiveData<User?>();
    val user: LiveData<User?> get() = _user

    fun setEmail(email:String) {
        _user.value = User(email, null, password = null)
    }

    fun setPassword(password:String) {
        _user.value = _user.value?.let { User(it.email, null, password = password) }
    }

    fun setName(name:String) {
        _user.value = _user.value?.let { User(it.email, name, password = it.password) }
    }

    fun setImageURI(uri:Uri){
        _user.value = _user.value?.let { User(it.email, it.name,  it.password, uri) }
    }

    fun clean() {
        _user.value = null
    }

}