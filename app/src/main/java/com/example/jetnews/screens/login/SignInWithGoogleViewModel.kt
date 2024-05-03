package com.example.jetnews.screens.login

import androidx.lifecycle.ViewModel
import com.example.jetnews.model.SignInResult
import com.example.jetnews.model.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInWithGoogleViewModel : ViewModel(){
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow() //immutable

    fun onSignInResult(result:SignInResult){
        _state.update { it.copy(
            isSignInSuccessful = result.userData != null,
            isError  = result.errorMessage
        ) }
    }

    fun resetState(){
        _state.update { SignInState() }
    }
}