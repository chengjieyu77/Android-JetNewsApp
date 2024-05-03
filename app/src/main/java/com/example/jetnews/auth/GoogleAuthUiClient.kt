package com.example.jetnews.auth

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.example.jetnews.R
import com.example.jetnews.model.MUser
import com.example.jetnews.model.SignInResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient:SignInClient
) {
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender?{
        val result = try{
            oneTapClient.beginSignIn( // by default these firebase function will return a task, a task is something
                                        // that executes something asynchronously, and we can add on success or on failure listener
                buildSignInRequest()
            ).await() // wait until finished
        }catch (e:Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken,null)
        return try{
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                userData = MUser(
                    userId = user?.uid.toString(),
                    displayName = user?.displayName.toString(),
                    id = null,
                    avatarUrl = user?.photoUrl.toString(),
                    quote = "",
                    profession = ""
                ),
                errorMessage =null
            )
        }catch (e:Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            SignInResult(
                userData = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun signOut(){
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        }catch (e:Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e

        }
    }

    fun getSignedInUser():MUser? = auth.currentUser?.run {
        MUser(
            userId = uid.toString(),
            id = null,
            displayName = displayName.toString(),
            quote = "",
            avatarUrl = "",
            profession = ""
        )
    }

    private fun buildSignInRequest():BeginSignInRequest{
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)//google authentication is supported
                    .setFilterByAuthorizedAccounts(false)//if you have already signed in an account, and it will only show
                                                                //that account as an option
                    .setServerClientId(context.getString(R.string.default_web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)//if you only have a single google account you can click cign in and it will automatically select that account
            .build()
    }
}