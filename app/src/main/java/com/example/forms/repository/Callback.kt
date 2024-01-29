package com.example.forms.repository

interface Callback {
    fun onSuccess()
    fun onError(message:String)
}