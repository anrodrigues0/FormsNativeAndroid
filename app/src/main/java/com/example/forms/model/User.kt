package com.example.forms.model

import android.net.Uri

data class User(
     val email:String,
     val name:String? ,
     val password: String?,
     val imgURL: Uri? = null
)
