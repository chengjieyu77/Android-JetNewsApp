package com.example.jetnews.screens.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnews.auth.AuthRepository
import com.example.jetnews.model.MUser
import com.example.jetnews.model.SignInState
import com.example.jetnews.utils.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repository:AuthRepository
) : ViewModel(){
    //val loadingState = MutableStateFlow(LoadingState.IDLE)//outside
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)//used internally
    val loading:LiveData<Boolean> = _loading // can be used outside

    val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    val _googleState = mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState

    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        repository.googleSignIn(credential).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _googleState.value = GoogleSignInState(success = result.data)
                }
                is Resource.Loading -> {
                    _googleState.value = GoogleSignInState(loading = true)
                }
                is Resource.Error -> {
                    _googleState.value = GoogleSignInState(error = result.message!!)
                }
            }


        }
    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = "Sign In Success "))
                }
                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }
                is Resource.Error -> {

                    _signInState.send(SignInState(isError = result.message))
                }
            }

        }
    }

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        repository.registerUser(email, password).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = "Register Success "))
                }
                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }
                is Resource.Error -> {

                    _signInState.send(SignInState(isError = result.message))
                }
            }

        }
    }

//    fun createUserWithEmailAndPassword(email:String,password:String,home:()->Unit){
//        if(_loading.value == false){
//            _loading.value = true
//            auth.createUserWithEmailAndPassword(email,password,)
//                .addOnCompleteListener { task ->
//                    if(task.isSuccessful){
//                        Log.d("FB","createUserWithEmailAndPassword:success ${task.result.toString()}")
//                        //m2@me.com
//                        val displayName = task.result?.user?.email?.split("@")?.get(0)
//                        //create user in database
//                        createUser(displayName)
//                        home.invoke()
//                    }else{
//                        Log.d("FB","createUserWithEmailAndPasswordrd: ${task.result.toString()}")
//                    }
//                    _loading.value = false
//                }
//        }
//    }
//
    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = MUser(id = null,
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "ss",
            profession = "ss",
            quote = "sss").toMap()
//        user["user_id"] = userId.toString()
//        user["display_name"] = displayName.toString()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
    }
//
//
//    fun signInWithEmailAndPassword(email: String,password: String,home:()->Unit)
//    = viewModelScope.launch{
//        try {
//            auth.signInWithEmailAndPassword(email,password)
//                .addOnCompleteListener { task ->
//                    if(task.isSuccessful){
//                        Log.d("FB","signInWithEmailAndPAssword:success ${task.result.toString()}")
//                        //go to home page
//                        home.invoke()
//                    }else{
//                        Log.d("FB","signInWithEmailAndPAssword: ${task.result.toString()}")
//                    }
//                }
//        }catch (ex:Exception){
//            Log.d("FB","signInWithEmailAndPassword: ${ex.message}")
//        }
//    }



}