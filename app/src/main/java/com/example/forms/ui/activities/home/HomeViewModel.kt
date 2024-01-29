package com.example.forms.ui.activities.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.forms.model.User
import com.example.forms.repository.auth.AuthRepository
import com.example.forms.repository.user.UserRepository

class HomeViewModel(private val repository: UserRepository, private val authRepository: AuthRepository):ViewModel() {

    private val _userInformation = MutableLiveData<User>()
    val userInformation:LiveData<User>  get() = _userInformation

    fun getUser(){
        _userInformation.value = repository.getUser()
    }

    fun logout(){
        authRepository.logout()
    }
}


class HomeViewModelFactory(private val repository: UserRepository, private val authRepository: AuthRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository, authRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
