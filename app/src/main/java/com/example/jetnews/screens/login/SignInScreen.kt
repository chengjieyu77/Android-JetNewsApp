package com.example.jetnews.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.jetnews.model.SignInState

@Composable
fun SignInScreen(
    state:SignInState,
    navController: NavController,
    onSignInClick:()->Unit = {}
    //viewModel: SignInWithGoogleViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
    val context = LocalContext.current

    LaunchedEffect(key1 = state.isError) {//this code of block will be called whenever state.signinerror changes
        state.isError?.let{error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(color = Color.White)){
        Button(onClick = onSignInClick,
            modifier = Modifier.padding(top = 100.dp)) {
            Text(text = "Sign in")
        }
    }
}