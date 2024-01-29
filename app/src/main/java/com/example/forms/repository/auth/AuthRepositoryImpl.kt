package com.example.forms.repository.auth

import android.util.Log
import com.example.forms.model.User
import com.example.forms.local.FakeLocal
import com.example.forms.repository.Callback

class AuthRepositoryImpl(private val fakeLocal: FakeLocal): AuthRepository {
    override fun registerUser(user: User) {
        fakeLocal.users.add(user)
    }

    override fun login(email: String, password: String, callback: Callback) {
        val user = fakeLocal.users.firstOrNull {
            email == it.email
        }
        if(user != null) {
            if(user.password == password) {
                fakeLocal.session = user.email
                callback.onSuccess()

            } else {
                callback.onError("user not found or password wrong")
            }
        } else {
            callback.onError("user not found or password wrong")
        }
    }

    override fun getSession():String? {
        return  fakeLocal.session
    }

    override fun logout() {
        fakeLocal.session = null
    }
}