package com.example.jetnews.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetnews.navigation.NewsScreen
import com.example.jetnews.ui.welcome.BBCCaption
import com.example.jetnews.ui.welcome.BBCLogo
import com.example.jetnews.ui.welcome.ContinueWithout
import com.example.jetnews.ui.welcome.RegisterButton
import com.example.jetnews.ui.welcome.SignInButton
import com.google.firebase.auth.FirebaseAuth


@Composable
fun WelcomeScreen(navController: NavController){
    //Check if there's a FB user, if so take them to home screen otherwise show welcome screen
//    if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
//        Surface(
//            modifier = Modifier
//                .fillMaxSize(),
//            color = Color.Black
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(start = 16.dp, top = 160.dp, end = 16.dp)
//                    .fillMaxWidth()
//            ) {
//
//                BBCLogo()
//                Spacer(modifier = Modifier.height(48.dp))
//                BBCCaption()
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 160.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    SignInButton(modifier = Modifier.fillMaxWidth()){
//                        navController.navigate(NewsScreen.LoginScreen.name)
//                    }
//                    RegisterButton(){
//                        // navController.navigate(NewsScreen.RegisterScreen.name)
//                    }
//                    Spacer(modifier = Modifier.height(16.dp))
//                    ContinueWithout(){
//                        navController.navigate(NewsScreen.HomeScreen.name)
//                    }
//                }
//
//
//            }
//        }
//    }else{
//        navController.navigate(NewsScreen.HomeScreen.name)
//    }
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 160.dp, end = 16.dp)
                .fillMaxWidth()
        ) {

            BBCLogo()
            Spacer(modifier = Modifier.height(48.dp))
            BBCCaption()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 160.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignInButton(modifier = Modifier.fillMaxWidth()){
                    navController.navigate(NewsScreen.LoginScreen.name)
                }
                RegisterButton(){
                    // navController.navigate(NewsScreen.RegisterScreen.name)
                }
                Spacer(modifier = Modifier.height(16.dp))
                ContinueWithout(){
                    navController.navigate(NewsScreen.HomeScreen.name)
                }
            }


        }
    }
}