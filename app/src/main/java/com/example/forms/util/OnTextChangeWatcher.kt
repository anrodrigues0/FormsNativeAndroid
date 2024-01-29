package com.example.forms.util

import android.text.Editable
import android.text.TextWatcher

class OnTextChangeWatcher(val onTextChange: (text:String) -> Unit):TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun afterTextChanged(p0: Editable?) {}
    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onTextChange(text.toString())
    }
}