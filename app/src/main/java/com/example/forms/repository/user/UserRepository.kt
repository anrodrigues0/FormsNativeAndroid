package com.example.forms.repository.user

import com.example.forms.model.User

interface UserRepository {
    fun getUser():User
}