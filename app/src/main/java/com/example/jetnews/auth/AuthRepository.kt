package com.example.jetnews.auth

import com.example.jetnews.utils.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.rpc.context.AttributeContext


import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>
}