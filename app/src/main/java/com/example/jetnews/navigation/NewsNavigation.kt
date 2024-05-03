package com.example.jetnews.navigation

import android.app.Activity.RESULT_OK
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetnews.auth.GoogleAuthUiClient
import com.example.jetnews.screens.article.ArticleScreen
import com.example.jetnews.screens.home.HomeScreen
import com.example.jetnews.screens.interests.InterestScreen
import com.example.jetnews.screens.login.LoginScreen
import com.example.jetnews.screens.login.SignInScreen
import com.example.jetnews.screens.login.SignInWithGoogleViewModel
import com.example.jetnews.screens.video.VideoDetailScreen
import com.example.jetnews.screens.video.VideoScreen
import com.example.jetnews.screens.welcome.WelcomeScreen
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun NewsNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NewsScreen.WelcomeScreen.name){
        composable(NewsScreen.WelcomeScreen.name){
            WelcomeScreen(navController = navController)
        }
        composable(NewsScreen.HomeScreen.name){
            HomeScreen(navController = navController)
        }
        composable(NewsScreen.ArticleScreen.name){
            ArticleScreen(navController = navController)
        }
        composable(NewsScreen.InterestScreen.name){
            InterestScreen(navController = navController)
        }
        composable(NewsScreen.VideoScreen.name){
            VideoScreen(navController = navController)
        }
        composable(NewsScreen.VideoDetailScreen.name){
            VideoDetailScreen(navController = navController)
        }
        composable(NewsScreen.LoginScreen.name){
            LoginScreen(navController = navController)
        }
//        composable(NewsScreen.SignInScreen.name){
//            val viewModel = viewModel<SignInWithGoogleViewModel>()
//            val state by viewModel.state.collectAsState()
//
//            val launcher = rememberLauncherForActivityResult(
//                contract = ActivityResultContracts.StartIntentSenderForResult()
//            ) {result ->
//                if(result.resultCode == RESULT_OK){
//                    scope.launch {
//                        val signInResult = googleAuthUiClient.signInWithIntent(
//                            intent = result.data ?:return@launch
//                        )
//                        viewModel.onSignInResult(signInResult)
//
//
//                    }
//                }
//
//            }
//            SignInScreen(state = state, navController = navController){
//                scope.launch {
//                   val signInIntentSender =  googleAuthUiClient.signIn()
//                    launcher.launch(
//                        IntentSenderRequest.Builder(
//                            signInIntentSender ?:return@launch
//                        ).build())
//                        }
//
//                }
//            }
        }

//        val route = NewsScreen.VideoDetailScreen.name
//        composable("$route/{videoId}",
//            arguments = listOf(
//                navArgument("videoId"){
//                    type = NavType.IntType
//                })){navBack->
//            navBack.arguments?.getInt("id").let {
//                videoId ->
//                VideoDetailScreen(navController = navController)
//            }
//
//        }
        //composable(NewsScreen.LiveScreen.name,
            //bottomNavId = selectedBottomNAvigationItem
        //){
            //LiveScreen(navController = navController)
        //}
        //composable(NewsScreen.MoreScreen.name,
            //bottomNavId = selectedBottomNAvigationItem
        //){
            //MoreScreen(navController = navController)
        //}
    }
