package com.example.jetnews.model

import com.example.jetnews.model.MUser

data class SignInResult(
    val userData : MUser?,
    val errorMessage:String?
) {
}