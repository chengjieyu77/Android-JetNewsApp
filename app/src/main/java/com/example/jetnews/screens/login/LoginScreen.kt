package com.example.jetnews.screens.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import com.example.jetnews.R
import com.example.jetnews.data.Constant.ServerClient
import com.example.jetnews.navigation.NewsScreen
import com.example.jetnews.ui.welcome.BBCLogo
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch


@OptIn(UnstableApi::class)
@Composable
fun LoginScreen(navController: NavController,
                viewModel: LoginScreenViewModel = hiltViewModel()
){
    val showLoginForm = rememberSaveable() {
        mutableStateOf(true)
    }
    val context = LocalContext.current
    val googleSignInState = viewModel.googleState.value
    val state = viewModel.signInState.collectAsState(initial = null)
    val scope = rememberCoroutineScope()

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }


    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 160.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){

            BBCLogo()
            Spacer(modifier = Modifier.height(30.dp))
            if(showLoginForm.value){
                UserForm(loading = false, isCreatedAccount = showLoginForm.value){email,password ->
                    Log.d("email and password","$email + $password")
                    //login an account
//                    viewModel.signInWithEmailAndPassword(email,password){
//                        navController.navigate(NewsScreen.HomeScreen.name)
//                    }

                    scope.launch {
                        viewModel.loginUser(email, password)
                        Log.d("googleSignInState",googleSignInState.toString())
                    }

                }
            }else{
                UserForm(loading = false, isCreatedAccount = showLoginForm.value){email,password ->
                    //create an account
//                    viewModel.createUserWithEmailAndPassword(email, password){
//                        navController.navigate(NewsScreen.HomeScreen.name)
//                    }
                    scope.launch {
                        viewModel.registerUser(email, password)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            SignUpLine(showLoginForm = showLoginForm.value){
                //navController.navigate(NewsScreen.RegisterScreen.name)
                showLoginForm.value = !showLoginForm.value
            }

            CustomOrDivider()
            
            ContinueWithGoogle(){
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(ServerClient)
                    .build()
                val googleSignInClient = GoogleSignIn.getClient(context,gso)

                launcher.launch(googleSignInClient.signInIntent)
            }
            LaunchedEffect(key1 = state.value?.isSuccess) {
                scope.launch {
                    if (state.value?.isSuccess?.isNotEmpty() == true) {
                        val success = state.value?.isSuccess
                        Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                        navController.navigate(NewsScreen.HomeScreen.name)
                    }
                }
            }

            LaunchedEffect(key1 = state.value?.isError) {
                scope.launch {
                    if (state.value?.isError?.isNotEmpty() == true) {
                        val error = state.value?.isError
                        Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                    }
                }
            }

            LaunchedEffect(key1 = googleSignInState.success) {
                scope.launch {
                    if (googleSignInState.success != null) {
                        Toast.makeText(context, "Sign In Success", Toast.LENGTH_LONG).show()
                    }
                }
            }


        }
    }
}


@Composable
fun ContinueWithGoogle(
    onClick: () -> Unit = {}
){
    Button(onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(2.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
    ) {
      Icon(painter = painterResource(id = R.drawable.google),
          contentDescription = "google icon",
          tint = Color.Unspecified,
          modifier = Modifier.size(25.dp))
      Text(text = "  Continue with Google")
    }
}

@Composable
fun UserForm(
    loading:Boolean = false,
    isCreatedAccount:Boolean = false,
    onDone: (String,String) -> Unit = {email,password ->}
){
    val email = rememberSaveable{ mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable{mutableStateOf(false)}
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value,password.value){
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    val modifier = Modifier
        .height(200.dp)
        .background(Color.Black)
        .verticalScroll(rememberScrollState())

    Column(modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInput(
            emailState = email,
            enabled = !loading,
            onAction = KeyboardActions{
                passwordFocusRequest.requestFocus()//it means when you are done, it's going to go ahead and focus on the next field
            })
        PasswordInput(modifier = Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            labelId = "Password",
            enabled = !loading,
            passwordVisibility = passwordVisibility,
            trailingIcon = {
                IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                    // Please provide localized description for accessibility services
                    val description = if (passwordVisibility.value) "Show password" else "Hide password"
                    if (passwordVisibility.value) Icon(painter = painterResource(id = R.drawable.visible), contentDescription = description ,
                        modifier = Modifier.size(25.dp),
                        tint = Color.White)
                    else Icon(painter = painterResource(id = R.drawable.hide) , contentDescription = description,
                        modifier = Modifier.size(25.dp),
                        tint = Color.White)
                }
            },
            onAction = KeyboardActions{
                if(!valid) return@KeyboardActions
                onDone(email.value.trim(),password.value.trim())
                keyboardController?.hide()
            })
        SubmitButton(
            text = if(isCreatedAccount) "Sign in" else "Sign up",
            loading = loading,
            validInputs = valid,
        ){
            onDone(email.value.trim(),password.value.trim())
            keyboardController?.hide()
        }




    }

}

@Composable
fun SubmitButton(text: String,
                 loading: Boolean,
                 validInputs: Boolean,
                 onClick: () -> Unit) {

        Button(onClick = onClick,
            colors = ButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
            shape = RectangleShape,
            modifier = Modifier.fillMaxWidth(0.8f),
            enabled = !loading && validInputs
        ) {
            Text(text = text,style = MaterialTheme.typography.titleSmall)
        }

}

@Composable
fun PasswordInput(modifier: Modifier,
                  passwordState: MutableState<String>,
                  labelId: String,
                  enabled: Boolean,
                  trailingIcon: @Composable () -> Unit,
                  passwordVisibility: MutableState<Boolean>,
                  imeAction: ImeAction = ImeAction.Done,
                  onAction: KeyboardActions = KeyboardActions.Default) {
    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = { Text(text = labelId,color = Color.White)},
        trailingIcon = trailingIcon,
        enabled = enabled,
        singleLine = true,
        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp, color = Color.White),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        //trailingIcon = {PasswordVisibility(passwordVisibility = passwordVisibility)},
        keyboardActions = onAction,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color.White,
            cursorColor = Color.White,
        )

    )

}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icons.Default.Close
    }
}


@Composable
fun EmailInput(modifier: Modifier = Modifier,
               emailState:MutableState<String>,
               labelId:String = "Email",
               enabled:Boolean = true,
               imeAction: ImeAction = ImeAction.Next,
               onAction:KeyboardActions = KeyboardActions.Default){
    InputField(
        modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        trailingIcon ={
             Icon(imageVector = Icons.Outlined.Email, contentDescription = "email icon",
                 tint = Color.White)
        },
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction
    )
}

@Composable
fun InputField(
    modifier:Modifier = Modifier,
    valueState:MutableState<String>,
    labelId:String,
    enabled: Boolean,
    isSingleLine:Boolean = true,
    trailingIcon:@Composable ()-> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default

){
    OutlinedTextField(value = valueState.value,
        onValueChange = {valueState.value = it},
        label = { Text(text = labelId,color = Color.White)},
        singleLine = isSingleLine,
        trailingIcon = trailingIcon,
        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp,
            color = Color.White),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType,
                                            imeAction = imeAction),
        keyboardActions = onAction,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color.White,
            cursorColor = Color.White,
        )
    )
}

@Composable
fun CustomOrDivider(){
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.width(70.dp)){
            HorizontalDivider(thickness = 2.dp,color = Color.White)
        }
        Text(text = " or ", style = MaterialTheme.typography.labelSmall,
            color = Color.White)
        Box(modifier = Modifier.width(70.dp)){
            HorizontalDivider(thickness = 2.dp,color = Color.White)
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun SignUpLine(
    showLoginForm: Boolean,
    onClick:()->Unit = {}
){
        Row {
            Text(text = if (showLoginForm) "New User? " else "Have an account? ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White)
            Text(text = if(showLoginForm) " Sign up" else "Log in",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onClick.invoke()
                })
        }


}