package com.example.forms.repository.user

import com.example.forms.local.FakeLocal
import com.example.forms.model.User

class UserRepositoryImpl(private val fakeLocal: FakeLocal):UserRepository {
    override fun getUser(): User {
        val emailSession = fakeLocal.session
        return fakeLocal.users.filter { it -> it.email == emailSession }[0]
    }
}