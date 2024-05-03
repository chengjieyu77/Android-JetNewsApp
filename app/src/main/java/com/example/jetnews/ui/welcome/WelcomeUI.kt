package com.example.jetnews.ui.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun BBCLogo(){
    Row(
        modifier = Modifier.fillMaxWidth(0.45f),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier
            .size(52.dp)
            .background(color = Color.White)
            ,
            contentAlignment = Alignment.Center){
            Text(text = "B", style = MaterialTheme.typography.displaySmall,
                color = Color.Black)
        }
        Box(modifier = Modifier
            .size(52.dp)
            .background(color = Color.White),
            contentAlignment = Alignment.Center){
            Text(text = "B",color = Color.Black,style = MaterialTheme.typography.displaySmall,)
        }
        Box(modifier = Modifier
            .size(52.dp)
            .background(color = Color.White),
            contentAlignment = Alignment.Center){
            Text(text = "C",color = Color.Black,style = MaterialTheme.typography.displaySmall)
        }
    }

}


@Composable
fun BBCCaption(){
    Column {
        Text(text = "Welcome to BBC", style = MaterialTheme.typography.displaySmall,
            color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "ALl the trusted news you're",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold)
        Text(text = "used to. And then some.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Discover what's new",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold)
    }

}

@Composable
fun SignInButton(
    modifier: Modifier,
    onSignInButtonClicked:()->Unit = {}
){
    Button(onClick = {onSignInButtonClicked()},
        colors = ButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Black
        ),
        shape = RectangleShape,
        modifier = modifier
    ) {
        Text(text = "Sign in",style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
fun RegisterButton(
    onRegisterButtonClicked:()->Unit = {}
){
    Button(onClick = {onRegisterButtonClicked()},
        colors = ButtonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Black
        ),
        shape = RectangleShape,
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(2.dp,color = Color.White)
    ) {
        Text(text = "Register for a BBC account",
            style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
fun ContinueWithout(
    onClicked:()-> Unit= {}
){
    Text(text = "Continue without account",
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.clickable {
            onClicked.invoke()
        })
}