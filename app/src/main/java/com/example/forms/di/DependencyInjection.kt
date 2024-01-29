package com.example.forms.di

import com.example.forms.local.FakeLocal
import com.example.forms.repository.auth.AuthRepository
import com.example.forms.repository.auth.AuthRepositoryImpl
import com.example.forms.repository.user.UserRepository
import com.example.forms.repository.user.UserRepositoryImpl

object DependencyInjection {
    fun AuthRepository():AuthRepository {
        return  AuthRepositoryImpl(FakeLocal)
    }

    fun UserRepository():UserRepository {
        return UserRepositoryImpl(FakeLocal)
    }
}