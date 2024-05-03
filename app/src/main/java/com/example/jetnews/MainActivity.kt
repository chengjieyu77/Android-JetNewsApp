package com.example.jetnews

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.jetnews.auth.GoogleAuthUiClient
import com.example.jetnews.navigation.NewsNavigation
import com.example.jetnews.ui.theme.JetNewsTheme
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    private val googleAuthUiClient by lazy {
//        GoogleAuthUiClient(
//            context =applicationContext,
//            oneTapClient = Identity.getSignInClient(applicationContext)
//        )
//    }
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNewsApp()
        }



    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            hideSystemUI()
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            showSystemUI()
    }

    private fun showSystemUI() {
        actionBar?.show()
        WindowCompat.setDecorFitsSystemWindows(window, true)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.apply {
                systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
        } else {
            window.insetsController?.apply {
                show(WindowInsets.Type.systemBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_DEFAULT
            }
        }
    }

    fun hideSystemUI() {
        //Hides the ugly action bar at the top
        actionBar?.hide()
        //Hide the status bars
        WindowCompat.setDecorFitsSystemWindows(window, false)

        WindowInsetsControllerCompat(window,  window.decorView).hide(WindowInsetsCompat.Type.systemBars())
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.apply {
                systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
            }
        } else {
            window.insetsController?.apply {
                hide(WindowInsets.Type.systemBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }


}


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun JetNewsApp() {
    JetNewsTheme {
//        val db = FirebaseFirestore.getInstance()
//        val user:MutableMap<String,Any> = HashMap()
//        user["firstName"] = "Jeo"
//        user["lastName"] = "James"
//
//        db.collection("users")
//            .add(user)
//            .addOnSuccessListener {
//                Log.d("listener success","success $it")
//            }.addOnFailureListener{
//                Log.d("listenerFailed","Failed $it")
//            }
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
            NewsNavigation()
            Log.d("local date",LocalDateTime.now().toString())

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNewsTheme {

    }
}