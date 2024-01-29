package com.example.forms.repository.auth

import com.example.forms.model.User
import com.example.forms.repository.Callback

interface AuthRepository {
    fun registerUser(user:User)
    fun login(email:String, password:String, callback: Callback)
    fun getSession():String?
    fun logout():Unit
}