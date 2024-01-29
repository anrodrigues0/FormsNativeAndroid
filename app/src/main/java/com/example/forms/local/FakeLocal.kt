package com.example.forms.local

import com.example.forms.model.User

object FakeLocal {

    val users: MutableList<User> = mutableListOf()
    var session: String? = null;

    init {
        users.add(User("admin@admin.com", "admin", "Admin@12345", null))
    }
}